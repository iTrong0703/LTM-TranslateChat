/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author TrongFlorida
 */
public class ModelRegister {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ModelRegister(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public ModelRegister() {

    }
    
    private String userName;
    private String password;
    

}
