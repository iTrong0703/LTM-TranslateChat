/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author TrongFlorida
 */
public class ModelReceiveMessage {

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ModelReceiveMessage(int fromUserID, String text) {
        this.fromUserID = fromUserID;
        this.text = text;
    }
    
    public ModelReceiveMessage(int chatID, int fromUserID, int toUserID, String text) {
        this.chatID = chatID;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }

    public ModelReceiveMessage() {
        
    }

    private int chatID;
    private int fromUserID;
    private int toUserID;
    private String text;
    
   
}
