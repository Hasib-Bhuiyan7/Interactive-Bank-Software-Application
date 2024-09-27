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
public class Manager extends User {
    public Manager(){
        super("admin", "admin", "Manager");
    }
    
    @Override
    public boolean login(String username, String password){
        if(username.equals("admin") && password.equals("admin")){
            System.out.println("Manager Account logged in: successful");
            return true;
        }
        else{
            System.out.println("Manager Account not logged in: failed ");
            return false;
        }
    }
     
    @Override
    public void logout(){
        System.out.println("Manager Account logged out: successful");
    }
    protected void addCustomer(String username, String password){
        File newCust = new File(username + ".txt");
        if(newCust.exists()){
            System.out.println("WARNING: Username already exists");
        }
        else if(!newCust.exists()){
            try{
                FileWriter writer = new FileWriter(newCust);
                writer.write("Username: \n" + username + "\n");
                writer.write("Password: \n" + password + "\n");
                writer.write("Balance: \n$" + 100.0 + "\n");
                writer.write("Level: \nSilver\n");
                writer.flush();
                writer.close();
                System.out.println("User " + username + "");
            } catch(IOException e){
                System.out.println("ERROR: File could not be written, please check file input\n");
            }
        }
        Customer custom = new Customer(username, password, 100.0);
    }
    
    protected void deleteCustomer(String username){
        File delCust = new File(username + ".txt");
        if(delCust.delete()){
            System.out.println("File deleted: Successful");
        }
        else{
            System.out.println("File could not be deleted: Failed");
        } 
    }
}
