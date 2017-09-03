package CSD207.Assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by gautam on 04/09/17.
 */

public class ObjectTokenizer {

    static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    static int objectCount = 0 , variableCount = 0;
    static ArrayList<String> objects = new ArrayList<>();
    static ArrayList<String> variables = new ArrayList<>();


    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = "";
        while (!(line = sc.readLine()).contentEquals("end")) {
            System.out.println(line);
            sb.append(line);
            sb.append('\n');

            if(line.contains("new") || line.contains("String")){
                objectCount++;
                int start = line.indexOf(" ");
                int end = line.indexOf(" ", start+1);
                objects.add(line.substring(start+1, end));

            }

            if(line.contains("int ") || line.contains("float ") || line.contains("double ") || line.contains("char ") || line.contains("boolean ")){
                variableCount++;
                int start = line.indexOf(" ");
                int end = line.indexOf(" ", start+1);
                variables.add(line.substring(start+1, end));
            }
        }
        System.out.format("There are %d variables and they are" + variables +"\n", variableCount);
        System.out.format("There are %d objects and they are" + objects +"\n", objectCount);

        //System.out.print(objectCount);

    }

}
