/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import event.PublicEvent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import model.ModelReceiveMessage;
import model.ModelSendMessage;
import model.ModelUserAccount;


public class Service {
    private static Service instance;
    private Socket client;

    private final int PORT_NUMBER = 9999;
    private final String IP = "localhost";
    private ModelUserAccount user;


    
    public static Service getInstance() {
        if(instance == null) {
            instance = new Service();
        }
        return instance;
    }
    
    private Service() {
    }
    
    public void startServer() {
        try {
            client = IO.socket("http://"+ IP +":"+ PORT_NUMBER);
            client.on("list_user", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    //list user
                    List<ModelUserAccount> users = new ArrayList<>();
                    for(Object o : os) {
                        ModelUserAccount u = new ModelUserAccount(o);
                        if(u.getUserID() != user.getUserID()) {
                            users.add(u);
                        }
                    }
                    PublicEvent.getInstance().getEventMenuLeft().newUser(users);
                } 
            });
            client.on("user_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    boolean status = (Boolean) os[1];
                    if(status) {
                        //Connect
                        PublicEvent.getInstance().getEventMenuLeft().userConnect(userID);
                    }else {
                        //Disconnect
                        PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userID);
                    }
                }
            });
            
            client.on("user_logged_in", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    // Gọi sự kiện "user_logged_in" trong class MenuLeft
                    PublicEvent.getInstance().getEventMenuLeft().userLoggedIn(userID);
                }
            });

            
            
            client.on("get_chat_history", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    // Nhận lịch sử chat từ server
                    List<ModelReceiveMessage> chatHistory = new ArrayList<>();
                    for (Object o : os) {
                        chatHistory.add(new ModelReceiveMessage(o));
                    }

                    // Hiển thị lịch sử chat
                    PublicEvent.getInstance().getEventChat().displayChatHistory(chatHistory);

                }
            });




            client.on("receive_ms", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    ModelReceiveMessage message = new ModelReceiveMessage(os[0]);
                    PublicEvent.getInstance().getEventChat().receiveMessage(message);
                }
            });
            client.open();
        } catch (URISyntaxException e) {
            error(e);
        }
    }
     
    public Socket getClient() {
        return client;
    }
    
    public ModelUserAccount getUser() {
        return user;
    }

    public void setUser(ModelUserAccount user) {
        this.user = user;
    }
     
    private void error(Exception e) {
        System.err.println(e);
    }
    
    public void sendChatMessage(int toUserID, String message) {
        if (client != null) {
            client.emit("send_to_user", new ModelSendMessage(user.getUserID(), toUserID, message));
        }
    }
    public void requestChatHistory(int fromUserID, int toUserID) {
        if (client != null) {
            client.emit("get_chat_history", new int[]{fromUserID, toUserID});


        }
    }

  

}
