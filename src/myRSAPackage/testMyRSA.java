/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myRSAPackage;

import java.math.BigInteger;

/**
 *
 * @author gerry
 */
public class testMyRSA {

    public static void main(String args[])
    {
        //p,q,e,m
        /*p=23; q=41; n=p*q;  e=19; m=35;   //m=mesg      */
        BigInteger bigIntM;
        BigInteger decodedMsg;
        BigInteger encMsg;

        myRSA rsa=new myRSA(23,41,19) ;

        rsa.printVector();

        //set our message to be 35 i.e m
        bigIntM=BigInteger.valueOf(35);
        System.out.println("\nM = " + bigIntM);
        encMsg=rsa.encodeMessage(bigIntM);
        System.out.println("Encoded Message = " + encMsg);
        System.out.println("Decoded Message = " + rsa.decodeMessage(encMsg));


        //msg=78
        bigIntM=BigInteger.valueOf(78);
        System.out.println("\nM = " + bigIntM);
        encMsg=rsa.encodeMessage(bigIntM);
        System.out.println("Encoded Message = " + encMsg);
        System.out.println("Decoded Message = " + rsa.decodeMessage(encMsg));





        //test ascii code coversion
        int iAsciiCode=rsa.convert_LettertoAscII("A");
        System.out.println("A = " + iAsciiCode);

        //test ascii code to letter
        String stmp=rsa.convert_AscIItoLetter(65);
        System.out.println("65 = " + stmp);


        //try to encode a sequesnce of letters
        String sWord="hello";
        String sEncodedString="";
                
        for ( int i = 0; i < sWord.length(); ++i )
        {
            char c = sWord.charAt( i );
            int j = (int) c;
            System.out.println(c + " = " + j);

            //encode letter in our string msg
            //bigM is the Ascii value of each letter in the string
            bigIntM=BigInteger.valueOf(j);
            
            System.out.println("\nM = " + bigIntM);
            encMsg=rsa.encodeMessage(bigIntM);
            System.out.println("Encoded Message = " + encMsg);

            sEncodedString=sEncodedString+encMsg.toString();

            decodedMsg=rsa.decodeMessage(encMsg);
            System.out.println("Decoded Message = " + decodedMsg);
        }
        System.out.println("\nFull Encrypted String " + sEncodedString);

    }

}
