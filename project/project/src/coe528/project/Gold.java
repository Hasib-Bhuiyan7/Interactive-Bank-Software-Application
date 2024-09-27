/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author hasib
 */
public class Gold extends Level{
    
    @Override 
    public Level updatelevelState(double balance){
        Level l = new Gold();
        if(balance<10000){
            l = new Silver();
        }
        else if(balance>20000){
            l = new Platinum();
        }
        return l;
    }
    
    @Override
    public String newLevelinString(){
        return("Gold");
    }
    
    @Override
    public double payFees(){
        return 10.00;
    }
    
}
