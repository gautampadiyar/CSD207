package CSD207.Assignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by gautam on 17/08/17.
 */

public class OperatorTable {


    public static void main (String[] args) throws java.lang.Exception
    {
        OperatorTable table = new OperatorTable();
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> a = new ArrayList<>(5);
        ArrayList<Integer> b = new ArrayList<>(5);

        for(int i=0; i<5; i++){
            System.out.print("a"+i+ ": ");
            a.add(i, sc.nextInt());
            System.out.print("b"+i+ ": ");
            b.add(i, sc.nextInt());
        }

        System.out.println("a\tb\ta&b\ta|b\t~(a&b)\ta>>b\ta<<b");
        for(int i=0; i<5; i++){
            table.printLine(a.get(i), b.get(i));
        }

    }

    public void printLine(int a, int b){
        System.out.println(a+"\t"+b+"\t"+this.and(a,b) +"\t"+ this.or(a,b) +"\t  "+ this.not(this.and(a,b)) +"\t"+this.right(a,b)+"\t   "+this.left(a,b));
    }

    public int and(int a,int b){
        return (a&b);
    }

    public int or(int a, int b){
        return (a|b);
    }

    public int not(int a){
        return ~a;
    }

    public int right(int a,int b){

        return (a>>b);
    }

    public int left(int a,int b){
        return (a<<b);
    }

}

