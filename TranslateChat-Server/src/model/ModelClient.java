/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.corundumstudio.socketio.SocketIOClient;

/**
 *
 * @author TrongFlorida
 */
public class ModelClient {

    public SocketIOClient getClient() {
        return client;
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }

    public ModelUserAccount getUser() {
        return user;
    }

    public void setUser(ModelUserAccount user) {
        this.user = user;
    }

    public ModelClient(SocketIOClient client, ModelUserAccount user) {
        this.client = client;
        this.user = user;
    }

    public ModelClient() {
    }

    private SocketIOClient client;
    private ModelUserAccount user;
}
