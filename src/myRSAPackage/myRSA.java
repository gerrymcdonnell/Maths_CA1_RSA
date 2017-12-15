/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myRSAPackage;

import java.util.Vector;
import java.math.BigInteger;
import newpackageV2.Equation2;

/**
 *
 * @author gerry
 */
public class myRSA {
    Vector <Equation2> v=new Vector();

    private int p,q,e,n,m;
    private int d=-1;

    public myRSA(int p, int q, int e) {
        this.p = p;
        this.q = q;
        this.e = e;

        //calculate d
        d=calculateD(p,q,e);

        System.out.println("\n\np = " + p);
        System.out.println("q = " + q);
        System.out.println("e = " + e);
        System.out.println("n = " + p*q);
        System.out.println("d = " + d);

    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }



    //methods
    public int calculateD(int p,int q,int e)
    {

        Equation2 eq;
        this.n=getN();

        int eularNum=getEularNum();

        int tmp;
        int a=0,b=0,d=0,k=0;

        boolean bStopCalc=false;


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
                //set d
                d=getDValue();
                return d;
            }
        }
        return -1;

    }




    public BigInteger decodeMessage(BigInteger C)
    {
        /*
        forumla
        C^d = M mod N
        need to find M the original msg
        */
        BigInteger ans,ans1,ans2,ans3,bigintN;
        //rase C to the power of d
        ans=C.pow(this.d);

        bigintN=BigInteger.valueOf(this.n);

        ans3=ans.mod(bigintN);

        /*System.out.println("c=" + C);
        System.out.println("d=" + d);
        System.out.println("ans="+ans);
        System.out.println("bigintN=" + bigintN);
        System.out.println("ans3=" + ans3);*/

        /*this will gives us M since
        as all we want is the mod (remainder) of C^d / N
         * ans1=C^d /N
         * ans2=ans1 * N
         * ans3=C^d - ans2
         */

        return ans3;
    }

    public int convert_LettertoAscII(String s)
    {
        char c = s.charAt(0);
        return (int)c;
    }

    public String convert_AscIItoLetter(int iAsciiCode)
    {
        //return Character.toString ((char) iAsciiCode))
        String aChar = new Character((char) iAsciiCode).toString();
        return aChar;
    }


    public BigInteger encodeMessage(BigInteger m)
    {
        //now we encryt/ decrypt msg
        //Encode message is encoded using public key
        //M^e = c mod N
        BigInteger bigintM,bigint2,bigintN;
        n=getN();
        bigintN=BigInteger.valueOf(n);

        //Raise M to the power e
        //bigintM=BigInteger.valueOf(m);
        //e=BigInteger.valueOf(e);

        bigintM=m.pow(e);

        //create a big int for N
        //bigintN=BigInteger.valueOf(n);

        //get the mod of M / N
        //this will be our encoded message
        bigint2=bigintM.mod(bigintN);

        return bigint2;

    }





    private int getDValue()
    {
        int iLastRow=v.size()-1;
        return v.elementAt(iLastRow).getB();
    }

    public void printVector()
    {
        System.out.println("\n\nPrinting Vector");
        for(int i=0;i<v.size();i++)
        {
            v.elementAt(i).print();
        }
    }

    public int getMod(int num1,int num2)
    {
        if(num1!=0 && num2!=0)
            return num1 % num2;
        else
            return 0;
    }

    public int getN(){
        return p*q;
    }

    public  int getEularNum(){
        return (this.p-1) * (this.q-1);
    }


        /**
     * Converts a byte array into its String representations
     * @param encrypted
     * @return
     */


    /**
     * encrypt byte array
     * @param message
     * @return
     */
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(BigInteger.valueOf(this.e), BigInteger.valueOf(this.n)).toByteArray();
    }

    /**
     * decrypt byte array
     * @param message
     * @return
     */
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(BigInteger.valueOf(this.d), BigInteger.valueOf(this.n)).toByteArray();
    }


}
