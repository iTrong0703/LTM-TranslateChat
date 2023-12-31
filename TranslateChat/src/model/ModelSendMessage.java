/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author TrongFlorida
 */
public class ModelSendMessage {

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ModelSendMessage(int fromUserID, int toUserID, String text) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }
    
    
    public ModelSendMessage() {
        
    }
    
    int fromUserID;
    int toUserID;
    String text;
    
    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("text", text);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
