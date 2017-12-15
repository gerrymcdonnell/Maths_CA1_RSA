/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackageV2;

/**
 *
 * @author gerry
 */
public class Equation2 {

    private int a=0;
    private int b=0;
    private int d=0;
    private int k=0;

    //a b d k

    public Equation2(int a,int b,int d,int k) {
        this.a=a;
        this.b=b;
        this.d=d;
        this.k=k;
    }

    public Equation2() {
    }


    public void print()
    {
        System.out.println(a + "," + b + "," + d + "," + k);
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }



}
