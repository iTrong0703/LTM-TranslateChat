/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;


public class ModelLogin {
    

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

    public ModelLogin(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    public ModelLogin() {
    }
    
    private String userName;
    private String password;
    
    
    public JSONObject toJsonObject() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("userName", userName);
            obj.put("password", password);
            return obj;
        } catch (JSONException e) {
            return null;
        }
    }
}
