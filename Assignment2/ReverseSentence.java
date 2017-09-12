package CSD207.Assignment2;

import java.util.Scanner;

/*
 * Created by gautam on 24/08/17.
 */

public class ReverseSentence {
    public static void main(String args[]){

        System.out.println("Enter a sentence");
        String a;
        Scanner sc = new Scanner(System.in);
        a = sc.nextLine();

        String[] splits = a.split("\\s+");  // handles any amount of whitespace
        //for (String s: splits) {System.out.println(s);}
        int n  = splits.length;
        for(int i=n-1; i>=0; i--){
            System.out.print(splits[i]+" ");
        }
    }
}
