package CSD207.Assignment3;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by gautam on 07/09/17.
 */

public class BillGeneration {

    ArrayList<Category> categoryList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();

    public static void main(String args[]){

    }
    public void taxCalculutor(Item i){

    }

    void loadAllItemsFromFile(){
        // for every new category encountered, create a new category object
        // and populate accordingly
    }

    void viewAllItems(){
        // show all categories
        // based on category selected, display items in that category
        // show GST information based on item selected
    }
}

class Bill{
    ArrayList<Item> itemsBought = new ArrayList<>();
    float billAmount, stateTax, centralTax;
    static int billId;   // sequential numbering

}

class Customer{
    UUID uid;
    String name;
    ArrayList<Bill> bills = new ArrayList<>();
    // list of all past bills
}

class Category{
    ArrayList<Item> items = new ArrayList<>();
    // list of items belonging to this category
    // category examples are grains, pulses, snacks ....
    float taxRate;

    void printAllItems(){

    }

    void loadFromJson(){
        // use gson API
    }
}

class Item{
    String name;
    float cost;
    Category category;  // to identify tax rate and what kind of item this is
    int nosBought;

    void displayInfo(){

    }

}
