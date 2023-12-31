/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TrongFlorida
 */
public class ModelMessage {

    
    
    
    private boolean action;

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ModelMessage() {
    }

    public ModelMessage(boolean action, String message, Object data) {
        this.action = action;
        this.message = message;
        this.data = data;
    }
    
    
    
    private String message;
    private Object data;
    
}
