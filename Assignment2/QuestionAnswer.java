package CSD207.Assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by gautam on 24/08/17.
 */

public class QuestionAnswer {

    ArrayList<Question> questions = new ArrayList<>();
    static int score;
    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {

        QuestionAnswer qObj = new QuestionAnswer();

        int choice = 0;

        do {
            System.out.println("Are you a student(1) or teacher(2) ?");

            choice = Integer.parseInt(sc.readLine());
            if (choice == 1) {

                System.out.println("How many questions would you like to answer?");
                int score = qObj.generateQuestions(Integer.parseInt(sc.readLine()));
                System.out.println("Your score was "+score);


            } else if (choice == 2) {
                // display
                // add
                // delete
                int option = 0;
                System.out.println("1. View questions");
                System.out.println("2. Add questions");
                System.out.println("3. Delete questions");

                option = Integer.parseInt(sc.readLine());

                switch (option){
                    case 1:
                        qObj.displayAllQuestions();
                        break;
                    case 2:
                        System.out.println("How many questions would you like to add?");
                        qObj.initQuestions(Integer.parseInt(sc.readLine()));
                        break;
                    case 3:
                        qObj.displayAllQuestions();
                        System.out.println("Enter question number to delete");
                        option = Integer.parseInt(sc.readLine());  // reusing variable
                        qObj.deleteQuestion(option);
                        break;
                }


            } else return;

            // show all options
           // System.out.println("Do you want to continue? 0 to exit");
           // choice = Integer.parseInt(sc.readLine());
            choice = 1;
            // stop infinite loop
        }while(choice == 1 || choice == 2);

    }

    public int generateQuestions(int n) throws IOException {

        int size = this.questions.size();
        if(n > size)
            System.out.format("Only %d questions available\n", size);

        int c;
        Question q;

        for(int j=0; j<n; j++){
            int x = randInt(0, size);
            q = this.questions.get(x);
            System.out.format("Q%d : ", j+1);
            q.ask();
            c = q.getAnswer(sc);
            if(q.checkAnswer(c))
                score++;
        }
        return score;
    }

    public int randInt(int min, int max) {

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min)) + min;

        return randomNum;
    }

    public void initQuestions(int n) throws IOException {
        Question temp = new Question();
        for(int i=0; i<n; i++){
            System.out.format("Enter question no %d ", i+1);
            String q = null;
            q = sc.readLine();
            temp.setQuestion(q);
            temp.setChoices(sc);
            this.questions.add(temp);
        }
    }

    public void displayAllQuestions(){
        int i=1;
        for (Question q: this.questions){
            System.out.print(i + " ");
            q.printQuestion();
            i++;
        }
    }

    public void deleteQuestion(int a){
        this.questions.remove(a);
    }


    class Question{
        String question;
        int correctAnswer;
        ArrayList<String> choices = new ArrayList<>(4);

        public void ask(){
            System.out.println(this.question);
            System.out.format("a) %s\t\tb) %s\n", this.choices.get(0), this.choices.get(1));
            System.out.format("c) %s\t\td) %s\n", this.choices.get(2), this.choices.get(3));
        }

        public void printQuestion(){
            System.out.println(this.question);
            for (int i=0; i<4; i++) {
                System.out.format("%d. "+this.choices.get(i)+"\n", i+1);
            }
            System.out.println();
        }

        public int getAnswer(BufferedReader sc) throws IOException {
            System.out.println("Please enter your answer");

            return Integer.parseInt(sc.readLine());

        }

        public boolean checkAnswer(int c){
            if(c == this.correctAnswer)
                return true;
            return false;
        }



        public void setChoices(BufferedReader sc) throws IOException {
            // to be used by teacher to initialize the questions
            for (int i=0; i<4; i++) {
                System.out.format("Enter choice %d : ", i+1);
                this.choices.add(sc.readLine());

            }
            int check = 0;
            do {
                System.out.print("What is the correct option number? ");
            }while(check<5 && check>0);
            this.correctAnswer = Integer.parseInt(sc.readLine());

        }

        public void setQuestion(String q){
            this.question = q;
        }

    }

}