/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
/**
 *
 * @author hasib
 */
public class Customer extends User {
    // Overview:Customer class is a mutable class, as the Balance variable of its attribute, the 'account' instance variable can be modified  
    // This class represents and defines the behaviour of a customer account, including all the customer acitivties like purchases, deposits and updating account info  
    //
    //
    // The abstraction function is:
    //    AF(Account) = getUsername() + account.getBalance() + account.updateLevel()
    //    getUsername() returns the username of the customer
    //    getBalance() is exceuted through its account, and it returns the current balance of account
    //    updateLevel() is executed through its account, and it determines the level of the account based on the balance:
    //                 ->If balance < $10000 = Silver
    //                 ->If balance < $20000 and balance > $10000 = Gold
    //                 ->If balance > $20000 = Platinum
    //
    // The rep invariant is:
    //    - Username cannot be NULL or empty
    //    - Password cannot be NULL or empty
    //    - Account cannot be NULL and Account Balance must be over $100
    //   
    //the rep
    
    //protected instance variable for customer's account
    protected Account acc;
    
    //Constructor
    public Customer(String username, String password, double bal){
        //Modifies: initializes Account variable
        //Requires: A string username that has not been used before, a string password, and a integer for the inital balance ofr hte account
        //Effects: Creates the customer object for the corresponding customer
        super(username, password, "customer");
        acc = new Account(bal);
        if(!repOK()){
            throw new IllegalArgumentException("ERROR: Invalid Line");            
        }
        File icust = new File(username + ".txt");
        if(!icust.exists()){
            try{
                FileWriter writer = new FileWriter(icust);
                writer.write("Username: \n" + username + "\n");
                writer.write("Password: \n" + password + "\n");
                writer.write("Balance: \n$" + bal + "\n");
                writer.write("Level: \n"+ acc.getstringLevel() +"\n");
                writer.flush();
                writer.close();
                //System.out.println("User " + username + "");
            } catch(IOException e){
                System.out.println("ERROR: File could not be written, please check file input\n");
            }
        }
    }
        
    //Getter
    protected String getUsername(){
        //Effects: Returns customer's Username
        return username;
    }
    protected double getBalance(){
        //Effects: Returns customer's Account Balance
        return acc.getBalance();
    }
    
    
    @Override
    protected boolean login(String username, String password){
        //Requires: A valid input of a string username and password, that corresponds to an existing username and passowrd from the customer files
        //Effects: Checks given input of username and password, compares with actual usernamne and password from customer(username.txt) file, and returns true if it matches
        String user = "";
        String pswd = "";
        File cust = new File(username+".txt"); //opening unique customer file with username
        try{
            Scanner scanner = new Scanner(cust);
            scanner.nextLine();
            user = scanner.nextLine();
            scanner.nextLine();
            pswd = scanner.nextLine();
            scanner.close();
            
            if(username.equals(user) && password.equals(pswd)){
                System.out.println("Account logged in: successful\n");
                return true;
            }
            else{
                System.out.println("Account not logged in: failed\n");
                return false;
            }
        }catch (FileNotFoundException e){
            System.out.println("Account not logged in: failed due to File error\n");
            return false;
        }
    }
    
    @Override
    protected void logout(){
        //Effects: Prints out logout message
        System.out.println("Account logged out: successful");
    }
    
    //Update original customer file, specifically the new balance left, and to determine the new level as per the transactions done by customer
    protected void updateCustfile(){
        //Modifies: changes and updates the customer info file(username.txt) by overwriting the balance and level of the account instance
        //effects: modifies username.txt for balance and level, prints message to indicate error or success if the file was properly modified  
        File cust = new File(username+".txt");
        try{
            Scanner scanner = new Scanner(cust);
            StringBuilder content = new StringBuilder(); //String variable holding modified string version of the text file
            int i=1;//Counter starting from the 1st line and tracking the line numbers
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();//Scanning each line 
                if(i==5){//Line 5 is the line for the heading "Balance: "
                    line = "Balance: $\n"+acc.getBalance();//Write new line over the heading line
                    scanner.nextLine();//Skipping next line that has the old balance
                    i++;
                }
                if(i==7){//Line 7 is the line for the heading "Level: "
                    line = "Level: \n"+ acc.getstringLevel();//Write new line over the heading line
                    scanner.nextLine();//Skipping next line that has the old level
                }
                i++;
                content.append(line).append("\n");//Adding line by line to the modified String version txt file, that is content
            }    
            scanner.close();
            FileWriter writer = new FileWriter(cust, false);
            writer.write(content.toString());//Insert updated and modified String content version back to the original file
            writer.flush();
            writer.close();
            System.out.println("File Updated: successful");
        }catch(IOException e){
            System.out.println("ERROR: File cannot be processed and updated");
        }
    }
    
    protected void deposit(double amount){
        //Modifies: Changes Customer's balance, retrieved from customer's 'Account' object, also changes customer's file content by updating it
        //Requires: An amount that is a double that is over $0
        //Effects: Updates customer's 'Account' object balance variable, and prints message indicating success or failure
        if(amount > 0){
            acc.setBalance(amount+acc.getBalance());
            updateCustfile();
            System.out.println("Deposit Successful: the new account baalnce is: " + acc.getBalance());
        }
        else{
            System.out.println("ERROR: Deposit value less than 0; this is not possible");
        }
    }
    
    protected void withdraw(double amount){
        //Modifies: Changes Customer's balance, retrieved from customer's 'Account' object, also changes customer's file content by updating it
        //Requires: An amount that is a double that is over $0 and less than the total account balance, as it must be taken away from the balance
        //Updates customer's 'Account' object balance variable, and prints message indicating success or failure
        if(amount > 0 && amount < acc.getBalance()){
            acc.setBalance(acc.getBalance()-amount);
            updateCustfile();
            System.out.println("Withdrawal Successful: the new account baalnce is: " + acc.getBalance());
        }
        else{
            System.out.println("ERROR: Withdrawal value is either less than $0 or more than the account balance");
        }
    }
    
    protected void purchase(String product, String seller, double price){
        //Modifies: Changes Customer's balance, retrieved from customer's 'Account' object, also changes customer's file content by updating it
        //Requires: A String indicaitng product, String indicating seller, and a double value that must be over $50
        //Effects: Changes customer's Account balance, and prints out purchase details and whether purchase was successful or not
        if((price > 50) && ((price + acc.payFee())<acc.getBalance())){
            withdraw(price + acc.payFee());
            System.out.println("Purchase Successful:\n Product: "+ product + "  Seller: "+ seller + "   Total Price: " + (price + acc.payFee()+"\n"));
        }
        else if(price < 50){
            System.out.println("ERROR: Purchase must be over $50");   
        }
        else{
            System.out.println("ERROR: Account Balance does not have enough money to carry out the transaction");
        }
    }
    
    public boolean repOK(){
        //Effects: Returns true if the rep invariant holds for this object; otherwise returns false
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || acc==null || acc.getBalance()<=0 ){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        //Effects: returns final String that includes customer name, balance, Account level. This implements the abstraction function 
        return ("Name: " + getUsername() + "\nbalance: " + acc.getBalance() + "\nLevel: " + acc.getstringLevel());
    }
 
}
