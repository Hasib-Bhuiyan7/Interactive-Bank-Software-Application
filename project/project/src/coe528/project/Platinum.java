/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author hasib
 */
public class Platinum extends Level{
    
    @Override 
    public Level updatelevelState(double balance){
        Level l = new Platinum();
        if(balance>10000 && balance<20000){
            l = new Gold();
        }
        else if(balance<10000){
            l = new Silver();
        }
        return l;
    }
    
    @Override
    public String newLevelinString(){
        return("Platinum");
    }
    
    @Override
    public double payFees(){
        return 0.00;
    }
    
}
