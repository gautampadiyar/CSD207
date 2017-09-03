package CSD207.Assignment1;
/**
 * Created by gautam on 17/08/17.
 */

import java.util.*;
import java.lang.*;

/*
*
*   1. Find all possible combinations of notes that can be dispensed (using recursive approach)
*   2. Add all these combinations in an ArrayList<Distribution> where distribution is a class with it's own properties
*   3. Rank these distributions based on two parameters
*       - All types of notes being present
*       - least no. of notes handed out
*   4. The best distribution based on the above to parameters is the one that should be given out
*
* */

class ATM
{
    int total, twoThousand, fiveHundred, oneHundred, withdrawal;
    public ArrayList<Distribution> solutionArray = new ArrayList<>();
    public ArrayList<Distribution> bestSol = new ArrayList<>();
    // do not dispense more than 10 of any note
    CustomComparator comp = new CustomComparator();

    public static void main(String args[])
    {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(100);arr.add(500);arr.add(2000);

        int m = arr.size();
        boolean printed = false;

        ArrayList<Integer> sol = new ArrayList<>();
        sol.add(0);sol.add(0);sol.add(0);

        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);
        System.out.println("How much money does the ATM have?");
        atm.setTotal(sc.nextInt());

        do {
            System.out.println("How many 2000 rupee notes?");
            atm.setTwoThousand(sc.nextInt());

            System.out.println("How many 500 rupee notes?");
            atm.setFiveHundred(sc.nextInt());

            System.out.println("How many 100 rupee notes?");
            atm.setOneHundred(sc.nextInt());

            // say 150, 100, 500

            if((atm.getFiveHundred()*500 + atm.getTwoThousand()*2000 + atm.getOneHundred()*100) != atm.getTotal())
                System.out.println("Total and entered notes do not match");
        }while(atm.getFiveHundred()*500 + atm.getTwoThousand()*2000 + atm.getOneHundred()*100 != atm.getTotal());

        do {
            atm.solutionArray.clear();
            atm.bestSol.clear();
            System.out.println("How much would you like to withdraw? Only enter multiples of hundred. Minimum amount 500");
            atm.setWithdrawal(sc.nextInt());

            System.out.format("There are %d ways of dispensing notes \n", atm.count(arr, m, atm.getWithdrawal(), sol));

            Collections.sort(atm.bestSol, atm.comp);
            System.out.println("\n\nThe optimal solutions are");
            for (Distribution e : atm.bestSol) {
                e.print();
            }

            System.out.println("The other solutions are");

            for (Distribution e : atm.solutionArray) {
                e.print();
            }

            System.out.println("The most optimal solution is");
            for (Distribution e : atm.bestSol) {
                if(atm.possibleToDispense(e)) {
                    e.print();
                    printed = true;
                    atm.reduceAmount(e);
                    break;
                }
            }

            if(!printed){
                for(int i=atm.solutionArray.size(); i>0; i--) {
                    Distribution e = atm.solutionArray.get(i);
                    if(atm.possibleToDispense(e)) {
                        e.print();
                        printed = true;
                        atm.reduceAmount(e);
                        break;
                    }
                }

            }

            if(!printed)
                System.out.println("Sorry, not possible to dispense notes");

            System.out.println("Would you like to continue withdrawing cash? O to exit");
        }while(sc.nextInt() != 0);

    }


    public int count( ArrayList<Integer> S, int noNotes, int sum, ArrayList<Integer> sol )
    {
        // If n is 0 then there is 1 solution (do not include any coin)
        if (sum == 0) {
            //System.out.println(sol);
            Distribution e = new Distribution(sol.get(0), sol.get(1), sol.get(2));
            if(e.containsAll() || this.getWithdrawal() < 2000)  // or total amount < 2000
                this.bestSol.add(e);
            else    this.solutionArray.add(e);

            return 1;
        }

        // If n is less than 0 then no solution exists
        if (sum < 0)
            return 0;

        // If there are no coins and n is greater than 0, then no solution exist
        if (noNotes <=0 && sum >= 1)
            return 0;

        // count is sum of solutions (i) including S[noNotes-1] (ii) excluding S[noNotes-1]
        int x = count(S, noNotes-1, sum, sol);
        sol.set(noNotes-1, sol.get(noNotes-1)+1);
        int y = count(S, noNotes, sum - S.get(noNotes - 1), sol);
        sol.set(noNotes-1, sol.get(noNotes-1)-1);
        return x + y;
    }

    public class CustomComparator implements Comparator<Distribution> {
        @Override
        public int compare(Distribution o1, Distribution o2) {
            return Integer.valueOf(o1.getNoNotes()).compareTo(Integer.valueOf(o2.getNoNotes()));
        }
    }

    public void reduceAmount(Distribution e){
        this.twoThousand -= e.getTwoThousands();
        this.fiveHundred -= e.getFiveHundreds();
        this.oneHundred -= e.getHundreds();

    }

    public boolean possibleToDispense(Distribution e){
        if(this.getTwoThousand() > e.getTwoThousands() && this.getFiveHundred() > e.getFiveHundreds() && this.getOneHundred() > e.getHundreds())
            return true;
        return false;
    }

    public void setTotal(int a){
        this.total = a;
    }

    public int getTotal(){
        return this.total;
    }

    public int getOneHundred() {
        return oneHundred;
    }

    public void setOneHundred(int oneHundred) {
        this.oneHundred = oneHundred;
    }

    public int getFiveHundred() {
        return fiveHundred;
    }

    public void setFiveHundred(int fiveHundred) {
        this.fiveHundred = fiveHundred;
    }

    public int getTwoThousand() {
        return twoThousand;
    }

    public void setTwoThousand(int twoThousand) {
        this.twoThousand = twoThousand;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public class Distribution{

        int hundreds, fiveHundreds, twoThousands, noNotes;

        public int getNoNotes() {
            return noNotes;
        }

        public Distribution(int a, int b, int c){
            this.hundreds = a;
            this.fiveHundreds = b;
            this.twoThousands = c;
            this.noNotes = a+b+c;
        }

        public void print(){
            System.out.println(this.getHundreds() + "x100" + " " + this.getFiveHundreds() + "x500" + " " + this.getTwoThousands() + "x2000");
        }

        public boolean containsAll(){

            if(this.hundreds > 0 && this.fiveHundreds > 0 && this.twoThousands > 0)
                return true;
            return false;
        }

        public int getHundreds() {
            return hundreds;
        }

        public int getFiveHundreds() {
            return fiveHundreds;
        }

        public int getTwoThousands() {
            return twoThousands;
        }

    }

}


