package CSD207.Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by gautam on 07/09/17.
 */

public class BillGeneration {

    static ArrayList<Category> categoryList = new ArrayList<>();
    static HashMap<Integer, Item> allItems = new HashMap<>();

    public static void main(String args[]) throws IOException {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int c = 1;

        do {
            if (categoryList.size() > 0) {
                switch (c) {
                    case 2:
                        System.out.print("Enter your id : ");
                        String id = sc.readLine();
                        if (!Customer.isNewCustomer(id))
                            System.out.println("Sorry, ID not found");
                        else {
                            Customer cust = Customer.getCustomer(id);
                            cust.bills.add(goToCart());
                            cust.showHistory();
                        }

                        goToCart();
                    case 1:
                        System.out.print("Enter your name : ");
                        String name = sc.readLine();
                        Customer temp = new Customer(name);
                        System.out.println("Your ID is " + temp.uid);
                        temp.bills.add(goToCart());
                }
            }

            takeInputsForItems();
            viewAllItems();
            populateAllItems();
            System.out.println("Are you a new customer?");
            System.out.println("1. yes");
            System.out.println("2. no");
            c = Integer.valueOf(sc.readLine());
        }while(c == 1);


    }

    public static Bill goToCart() throws IOException{
        System.out.println("What would you like to buy?");
        System.out.println("Enter item number and number of units");

        //Item item = allItems.get(id);

        Bill b = new Bill(allItems);
        b.populateCart();
        b.displayBill();
        b.calculateBill();
        return b;
    }

    public static void takeInputsForItems() throws IOException {
        // for every new category encountered, create a new category object
        // and populate accordingly
        System.out.println("Enter categories with tax rate, 'end' to end ");
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!(line = sc.readLine()).contentEquals("end")) {
            int n = line.lastIndexOf(",");
            String name = line.substring(0, n);
            int tax = Integer.valueOf(line.substring(n + 2));
            categoryList.add(new Category(name, tax));
        }

        int i = 1, j = 1;
        // i = category number
        // j = item number

        for (Category c : categoryList) {
            System.out.println("Enter the items in the " + c.name + " category");
            while (!(line = sc.readLine()).contentEquals("end")) {
                int n = line.lastIndexOf(",");
                String name = line.substring(0, n);
                int cost = Integer.valueOf(line.substring(n + 2));
                c.items.add(new Item(i * 100 + j, name, cost, c));
                j++;
            }
            i++;
            j = 1;  // reset item number
        }
    }

    public static void viewAllItems() {
        // show all categories
        // based on category selected, display items in that category
        // show GST information based on item selected

        for (Category c : categoryList) {
            System.out.println("\nCategory " + c.name + ", Tax Rate " + c.taxRate + "%");
            for (Item item : c.items) {
                item.displayInfoId();
            }
            System.out.print("\n");
        }

    }

    public static void populateAllItems() {
        for (Category c : categoryList) {
            for (Item item : c.items) {
                allItems.put(item.itemID, item);
            }
        }
    }
}

class Bill{
    ArrayList<Item> itemsBought = new ArrayList<>();
    HashMap<Integer, Item> allItems = new HashMap<>();
    float billAmount, stateTax, centralTax, sum;
    UUID billId;   // sequential numbering

    Bill(HashMap<Integer, Item> allItems){
        this.allItems = allItems;
        billId = UUID.randomUUID();
    }

    public void addItem(Item item){
        itemsBought.add(item);
    }

    public void populateCart() throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!(line = sc.readLine()).contentEquals("end")) {
            int n = line.lastIndexOf(",");
            int id = Integer.valueOf(line.substring(0, n));
            int nos = Integer.valueOf(line.substring(n + 2));
            Item item = allItems.get(id);
            item.nosBought = nos;
            this.addItem(item);
        }

    }

    public void calculateBill() {
        int sum = 0;
        int tax = 0;
        for (Item i : this.itemsBought) {
            sum += i.nosBought * i.cost;
            tax += (sum * i.category.taxRate)/100;
        }
        this.centralTax = tax/2;
        this.stateTax = tax/2;
        this.sum = sum;
        this.billAmount = sum + stateTax + centralTax;
    }

    public void displayBill(){
        for(Item i: itemsBought){
            System.out.print(i.nosBought + " x ");
            i.displayInfo();
        }
        calculateBill();
        System.out.println("\nTotal Amount = ₹" + this.sum);
        System.out.println("SGST = ₹" + this.stateTax);
        System.out.println("CGST = ₹" + this.centralTax);
        System.out.println("\nAmount to pay = ₹" + this.billAmount);


    }

}

class Customer{
    String uid;
    String name;
    ArrayList<Bill> bills = new ArrayList<>();
    // list of all past bills

    static HashMap<String, Customer> customers = new HashMap<>();

    Customer(){

    }

    Customer(String name){
        this.name = name;
        generateId();
    }

    public void showHistory(){
        System.out.println("Customer name : "+ this.name);
        System.out.println("Customer id : "+ this.uid);
        int i=1;
        for(Bill b: bills) {
            System.out.println("Bill "+i);
            b.displayBill();
            i++;
        }

    }

    public void generateId(){
        this.uid = UUID.randomUUID().toString();
    }

    public static boolean isNewCustomer(String id){
        if(customers.containsKey(id))
            return true;
        else return false;
    }

    public static Customer getCustomer(String id){
        return customers.get(id);
    }
}

class Category{
    ArrayList<Item> items = new ArrayList<>();
    // list of items belonging to this category
    // category examples are grains, pulses, snacks ....
    int taxRate;
    String name;

    public Category(String name, int taxRate) {
        this.taxRate = taxRate;
        this.name = name;
    }
}

class Item{
    String name;
    int cost;
    Category category;  // to identify tax rate and what kind of item this is
    int nosBought;
    int itemID;

    public Item(String name, int cost, Category category, int nosBought) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.nosBought = nosBought;
    }
    // for adding to shop

    public Item(int id, String name, int cost, Category category) {
        this.itemID = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
    }
    // for adding to cart

    public void displayInfoId(){
        System.out.println(this.itemID + ". " + this.name + ", ₹" + this.cost);
    }

    public void displayInfo(){
        System.out.println(this.name + ", ₹" + this.cost);
    }

}


/*

example input

Grains, 5
Pulses, 5
Snacks, 28
Toiletries, 18
end
Rice, 45
Wheat, 50
Oats, 60
Millets, 70
end
Rajma, 30
Bengal gram, 40
Moong, 25
Urad, 35
end
Lays, 20
Marrie, 25
Maggi, 25
Bhujia, 40
end
Toothpaste, 30
Soap, 20
Shampoo, 80
Facewash, 70
end

101, 2
103, 1
202, 2
204, 5
303, 2
402, 1
end

*/
