/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import model.ModelClient;
import model.ModelLogin;
import model.ModelMessage;
import model.ModelReceiveMessage;
import model.ModelRegister;
import model.ModelSendMessage;
import model.ModelUserAccount;

/**
 *
 * @author TrongFlorida
 */
public class Service {
    private static Service instance;
    private SocketIOServer server;
    private ServiceUser serviceUser;
    private List<ModelClient> listClient;
    private JTextArea textArea;
    private final int PORT_NUMBER = 9999;
    
    public static Service getInstance(JTextArea textArea) {
        if(instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }
    
    private Service(JTextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser();
        listClient = new ArrayList<>();
    }
    
    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                textArea.append("One client connected\n");
            }
            
        });
        
        server.addEventListener("register", ModelRegister.class, new DataListener<ModelRegister>() {
            @Override
            public void onData(SocketIOClient sioc, ModelRegister t, AckRequest ar) throws Exception {            
                ModelMessage message = serviceUser.register(t);
                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
                if(message.isAction()) {
                    textArea.append("User has Register: "+ t.getUserName() + ", Pass: "+ t.getPassword() +"\n");
                    server.getBroadcastOperations().sendEvent("list_user", (ModelUserAccount) message.getData());
                    addClient(sioc, (ModelUserAccount) message.getData());
                } 
            }
        });
        
        server.addEventListener("login", ModelLogin.class, new DataListener<ModelLogin>() {
            @Override
            public void onData(SocketIOClient sioc, ModelLogin t, AckRequest ar) throws Exception {
                ModelUserAccount login = serviceUser.login(t);
                if (login != null) {
                    ar.sendAckData(true, login);
                    addClient(sioc, login);
                    userConnect(login.getUserID());
                    
                     // Gửi sự kiện mới "user_logged_in" với thông tin người dùng, bao gồm cả ID của người dùng
                    sioc.sendEvent("user_logged_in", login.getUserID());
                } else {
                    ar.sendAckData(false);
                }
            }
        });
        
        server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    List<ModelUserAccount> list = serviceUser.getUser(userID);
                    sioc.sendEvent("list_user", list.toArray());
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });
        
        server.addEventListener("send_to_user", ModelSendMessage.class, new DataListener<ModelSendMessage>() {
            @Override
            public void onData(SocketIOClient sioc, ModelSendMessage t, AckRequest ar) throws Exception {
                sendToClient(t);
            }          
        });
        
        
        server.addEventListener("get_chat_history", int[].class, new DataListener<int[]>() {
            @Override
            public void onData(SocketIOClient sioc, int[] data, AckRequest ar) throws Exception {
                System.out.println("Received get_chat_history event from client");
                sendChatHistoryToClient(data[0], data[1], sioc);
            }
        });




        
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient sioc) {
                int userID = removeClient(sioc);
                if(userID != 0) {
                    //removed
                    userDisconnect(userID);
                }
            }
            
        });
        server.start();
        textArea.append("Server has start on port: "+ PORT_NUMBER +"\n");
    }
    
    private void userConnect(int userID) {
        server.getBroadcastOperations().sendEvent("user_status", userID, true);
    }
    
    private void userDisconnect(int userID) {
        server.getBroadcastOperations().sendEvent("user_status", userID, false);
    }
    
    private void addClient(SocketIOClient client, ModelUserAccount user) {
        listClient.add(new ModelClient(client, user));
    }
    
    private void sendToClient(ModelSendMessage data) {
        for (ModelClient c : listClient) {
            if (c.getUser().getUserID() == data.getToUserID()) {
                c.getClient().sendEvent("receive_ms", new ModelReceiveMessage(data.getFromUserID(), data.getText()));

                // Save data to the database here
                serviceUser.saveChatHistory(data);

                break;
            }
        }
    }
    
    public int removeClient(SocketIOClient client) {
        for (ModelClient m : listClient) {
            if(m.getClient() == client) {
                listClient.remove(m);
                return m.getUser().getUserID();
            }
        }
        return 0;
    }
    

    
    public List<ModelClient> getListClient() {
        return listClient;
    }
    
    private void sendChatHistoryToClient(int fromUserID, int toUserID, SocketIOClient requester) {
        List<ModelReceiveMessage> chatHistory = serviceUser.getChatHistory(fromUserID, toUserID);
        requester.sendEvent("get_chat_history", chatHistory.toArray());
        System.out.println("Sent chat_history to the requesting client");
    }
    
}
