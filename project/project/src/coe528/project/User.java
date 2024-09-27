/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

public abstract class User {
    
    protected String username;
    protected String password;
    protected String role;
    
    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    //Abstract methods that perform general operations for abstract class
    abstract boolean login(String username, String password);
    abstract void logout();
}
