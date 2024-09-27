/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author hasib
 */
public abstract class Level {
    
    public abstract Level updatelevelState(double balance);
    
    public abstract String newLevelinString();
    
    public abstract double payFees();
    
}
