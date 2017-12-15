package myapp;


import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gerry
 */
public class mainApp {

static Vector <Equation>v=new Vector();

    public static void main(String args[])
    {
        int iSearchNum=-1;
        int iNumToFind=-1;


        expressNum1inTermsofNum2(21877,761);
        //expressNum1inTermsofNum2(3696,2609);
        //expressNum1inTermsofNum2(120,23);

        //421, 111
        //expressNum1inTermsofNum2(421,111);


        System.out.println("Print out our equations:");
        System.out.println("===================================");
        for(int i=0;i<v.size();i++)
        {
            v.elementAt(i).display();
        }


        System.out.println();



        System.out.println("Print out our equations in reverse:");
        System.out.println("===================================");
        for(int i=v.size()-1;i>=0;i--)
        {
            v.elementAt(i).displayReverse();
        }



        //test finding number values i.e find the equation which reprents a number

        System.out.println("\n\nSearch for the equation of a number: 217");
        System.out.println("===================================");

        //here we start at the bootom of the vector of equations and take the secod number
        //i.e 435=2(217) + 1
        //we want to get num1 i.e 217
        iNumToFind=v.elementAt(v.size()-1).getNum1();

        if(iNumToFind!=-1)
        {
            iSearchNum=findEquation(iNumToFind,v);

            if(iSearchNum!=-1)
            {
                v.elementAt(iSearchNum).displayReverse();
            }
            System.out.println("===================================");
        }
        else{
            System.out.println("Error.");
        }

        /*now we need to put the value of inumtofind into our equation
         * i.e our equation 1=435 - 2(217)
         * we find 217
         * we know 217=1087 - 2(435)
         * so we end up with
         * 1=435 - 2(1087 - 2(435))
         *
         */


        /*expressNum1inTermsofNum2(27,8);

        expressNum1inTermsofNum2(29,5);*/

        

        /*isCoPrime(3696,2609);
        isCoPrime(321,96);
        isCoPrime(131,14);*/
    }

    public static void expressNum1inTermsofNum2(int num1,int num2)
    {
        int mod;
        String stmp="";
        mod=getMod(num1,num2);      

        Equation e=new Equation(num1,getDivisor(num1,num2),num2,mod);

        //System.out.println("The Mod of " + num1 + "/" + num2 + " is " + mod);
        //stmp=num1 + " = " + getDivisor(num1,num2) + "(" + num2 +") " + "+ " + mod;
        
        v.add(e);

        //System.out.println(stmp);        

        num1=num2;
        num2=mod;        

        //e.display();
        //e.displayReverse();
        
        if(num2!=1)
        {
            expressNum1inTermsofNum2(num1,num2);
        }
        else{
            System.out.println();
            System.out.println();
        }        
        
    }



    //2 nums are co prime
    /*public static boolean isCoPrime(int num1,int num2)
    {
        if(num1%num2==1){
            System.out.println(num1 + " & " + num2 + " are Co-Prime.");
            return true;
        }
        else{
            System.out.println(num1 + " & " + num2 + " are NOT Co-Prime.");
            return false;
        }
    }*/


    public static int getDivisor(int num1,int num2)
    {
        return num1/num2;
    }

    public static int getMod(int num1,int num2)
    {
        return num1%num2;
    }


    //utils
    //we want to find the equation that num2 is =
    //eg pass in 217 and get 217=1087 -2(435)
    public static int findEquation(int iNumtofind,Vector<Equation> v)
    {
        for(int i=0;i<v.size();i++)
        {
            if(v.elementAt(i).getNum2()==iNumtofind)
                return i;
        }
        return -1;
    }


}
