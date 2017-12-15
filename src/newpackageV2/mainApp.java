/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackageV2;

import java.util.Vector;
import java.math.BigInteger;

/**
 *
 * @author gerry
 * v2 using the table method
 */
public class mainApp {
static Vector <Equation2> v=new Vector();

    public static void main(String args[])
    {
        BigInteger m;
        /*p=23; q=41; n=p*q;  e=19; m=35;   //m=mesg      */

        m=BigInteger.valueOf(35);
        rsaTest1(23,41,19,m);
        
        m=BigInteger.valueOf(67);
        rsaTest1(23,41,19,m);

    }

    public static void rsaTest1(int p,int q, int e,BigInteger m)
    {
        int d;

        //clear vector
        v.clear();
        BigInteger n=BigInteger.valueOf(0);

        n=n.valueOf(getN(p,q));


        //calculate d
        d=calculateD(p,q,e);
        System.out.println("d = " + d);
        
        printVector();

        System.out.println("\n\nd =" + getDValue());

        System.out.println("\n\nPublic key (n,e)= (" + n + "," +e +")");
        System.out.println("Private key (n,d) = (" + n + "," +getDValue() +")");

        System.out.println("\n\n");
        System.out.println("Message M = " + m);

        //encode message
        BigInteger encMsg=encodeMessage(m,n,e);
        System.out.println("Encoded Message = " +encMsg );


        //decode message
        d=getDValue();
        BigInteger decodedMsg=decodeMessage(encMsg,n,d);
        System.out.println("Decoded Message = " +decodedMsg );
    }


    public static int calculateD(int p,int q,int e)
    {
        
        Equation2 eq;
        int n=getN(p,q);
        int eularNum=getEularNum(p,q);
        
        int tmp;
        int a=0,b=0,d=0,k=0;    

        boolean bStopCalc=false;

        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("n = " + n);
        System.out.println("eularnum = " + eularNum);


        //***********************************************
        //create the first row of our table and add to vector
        int currentRow=-1;
        currentRow++;
        eq=new Equation2(1,0,eularNum,0);
        v.add(eq);
        //***********************************************


        //***********************************************
        //create 2nd row of vector
        currentRow++;
        tmp=eularNum/e;
        eq=new Equation2(0,1,e,tmp);
        v.add(eq);
        //***********************************************


       //while there is no 1 value in column 3
       while(bStopCalc==false)
       {
            //***********************************************
            //third row
            eq=new Equation2();
            currentRow++;
            //need to do some calculations
            //get d first
            //d=the mod of the row0.d/row1.d
            d=getMod(v.elementAt(currentRow-2).getD(),v.elementAt(currentRow-1).getD())  ;
            eq.setD(d);

            //calc k
            //d value from previous row / d value of current row
            if(v.elementAt(currentRow-1).getD()!=0 && d!=0)
            {
                k=v.elementAt(currentRow-1).getD() / d;
                eq.setK(k);
            }

            
            //calc a
            //a=((currentRow-2).a - (currentRow-1).a) *(currentRow-1).k
            a=(v.elementAt(currentRow-2).getA()-v.elementAt(currentRow-1).getA() * v.elementAt(currentRow-1).getK());
            eq.setA(a);

            //calc b
            //b is almost the same
            b=(v.elementAt(currentRow-2).getB()-v.elementAt(currentRow-1).getB() * v.elementAt(currentRow-1).getK());
            eq.setB(b);

            //add object to vector
            v.add(eq);
            //***********************************************

            //check is d at one
            if(d==1)
            {
                bStopCalc=true;
            }
        }
        //we found d return
        return d;
    }
    



    private static  BigInteger decodeMessage(BigInteger C,BigInteger N,int D)
    {
        /*
        forumla
        C^d = M mod N 
        need to find M the original msg
        */
        BigInteger ans,ans1,ans2,ans3,bigintN;
        //rase C to the power of d
        ans=C.pow(D);

        //bigintN=BigInteger.valueOf(N);

        /*this will gives us M since
        as all we want is the mod (remainder) of C^d / N
         * ans1=C^d /N
         * ans2=ans1 * N
         * ans3=C^d - ans2
         */

        ans3=ans.mod(N);
        return ans3;

    }

    private static BigInteger encodeMessage(BigInteger m,BigInteger n,int e)
    {
        //now we encryt/ decrypt msg
        //Encode message is encoded using public key
        //M^e = c mod N

        BigInteger bigintM,bigint2;   

        //Raise M to the power e
        //bigintM=BigInteger.valueOf(m);
        //e=BigInteger.valueOf(e);

        bigintM=m.pow(e);

        //create a big int for N
        //bigintN=BigInteger.valueOf(n);

        //get the mod of M / N
        //this will be our encoded message
        bigint2=bigintM.mod(n);

        return bigint2;      

    }
    




    private static int getDValue()
    {
        int iLastRow=v.size()-1;
        return v.elementAt(iLastRow).getB();
    }

    private static void printVector()
    {
        System.out.println("\n\nPrinting Vector");
        for(int i=0;i<v.size();i++)
        {
            v.elementAt(i).print();
        }
    }

    private static int getMod(int num1,int num2)
    {
        if(num1!=0 && num2!=0)
            return num1 % num2;
        else
            return 0;
    }

    private static int getN(int p,int q){
        return p*q;
    }

    private static int getEularNum(int p,int q){
        return (p-1) * (q-1);
    }



}
