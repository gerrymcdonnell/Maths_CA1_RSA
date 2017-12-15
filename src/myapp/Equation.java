package myapp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gerry
 */
public class Equation {
    private int ans;
    private int multiplier;
    private int num1;
    private int num2;

    public Equation() {
    }



    public Equation(int ans, int multiplier, int num1, int num2) {
        this.ans = ans;
        this.multiplier = multiplier;
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void display()
    {
        System.out.println(ans + "=" + multiplier + "(" + num1 + ") + " + num2);
    }

    public void displayReverse()
    {
        System.out.println(num2 + "=" + ans + " - " + multiplier + "(" + num1 + ")");
    }
}
