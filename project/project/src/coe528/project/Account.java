/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author hasib
 */
public class Account {
    private double balance;
    private Level level;
    
    //Constructor
    public Account(double balance){
        this.balance = balance;
        this.level = new Silver();
        this.level = getLevel();
    }
    
    //Setters and Getters
    protected double getBalance(){
        return balance;
    }
    protected void setBalance(double balance){
        this.balance = balance;
    }
    
    
    //Determines level based on bank balance amount
    public Level getLevel(){
        level = level.updatelevelState(balance);
        return level;
    }
    
    public String getstringLevel(){
        getLevel();
        return level.newLevelinString();
    }
    
    //Changes and sets the level as required, acts a setter method for Account level
    public void changeLevel(Level l){
        level = l;
    }
    
    public double payFee(){
        getLevel();
        return level.payFees();
        /*getLevel();
        if(stringLevel.equals("Silver")){
            return 20.0;
        }
        else if(stringLevel.equals("Gold")){
            return 10.0;
        }
        else{
            return 0.0;
        }*/
    }
    
    
    
}
