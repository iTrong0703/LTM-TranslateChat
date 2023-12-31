/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import com.cybozu.labs.langdetect.LangDetectException;
import component.ChatBody;
import component.ChatBottom;
import component.ChatTitle;
import event.IEventChat;
import event.PublicEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelReceiveMessage;
import model.ModelSendMessage;
import model.ModelUserAccount;
import net.miginfocom.swing.MigLayout;
import service.Service;

/**
 *
 * @author TrongFlorida
 */
public class ChatBox extends javax.swing.JPanel {

    private ChatTitle chatTitle;
    private ChatBody chatBody;
    private ChatBottom chatBottom;
    private List<ModelReceiveMessage> liChatHistory;
    
    public ChatBox() {
        initComponents();
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, bottom]0[shrink 0]0"));//[]0[100%, bottom]0[] đẩy khung chat input xuống cuối cùng, bottom cho tin nhắn mới gửi đẩy từ dưới lên
        chatTitle = new ChatTitle();
        chatBody = new ChatBody();
        chatBottom = new ChatBottom();
        PublicEvent.getInstance().addEventChat(new IEventChat() {
            @Override
            public void sendMessage(ModelSendMessage data) {
                chatBody.addItemRight(data);
            }

            @Override
            public void receiveMessage(ModelReceiveMessage data) {
                if(chatTitle.getUser().getUserID() == data.getFromUserID()) {
                    chatBody.addItemLeft(data);
                }
            }

            @Override
            public void displayChatHistory(List<ModelReceiveMessage> chatHistory) {
                liChatHistory = chatHistory;
                updateChatHistoryList(chatHistory);
            }

            @Override
            public void translateChatHistory(String sourceLang, String targetLang) {
                System.out.println("ChatBox: "+ sourceLang + targetLang);
                try {
                    updateTransChatHistoryList(sourceLang, targetLang);
                } catch (IOException ex) {
                    Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LangDetectException ex) {
                    Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void originalChatHistory() {
                updateOriChatHistoryList();
            }

           

           

            
            
        });
        add(chatTitle, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::40%");// Chiều cao đẩy lên của chat bottom k quá 40%
        
    }

    
    public void setUser(ModelUserAccount user) {
        chatTitle.setUserName(user);
        chatBottom.setUser(user);
        chatBody.clearChat();
    }
    
    public void updateUser(ModelUserAccount user) {
        chatTitle.updateUser(user);
    }
    
    
    private void updateChatHistoryList(List<ModelReceiveMessage> chatHistory) {
        chatBody.clearChat();
        ModelUserAccount currentUser = chatTitle.getUser();
        if (currentUser == null) {
            // Xử lý nếu chatTitle.getUser() trả về null
            return;
        }

        for (ModelReceiveMessage message : chatHistory) {
            System.out.println(".updateChatHistoryList(): " + message.getFromUserID());
            if (currentUser.getUserID() == message.getFromUserID()) {
                
                
                chatBody.addItemLeft(message);
                
            } else {
                chatBody.addItemRight(message);
            }
        }
        
    }
    
    private void updateTransChatHistoryList(String sourceLang, String targetLang) throws IOException, LangDetectException {
        chatBody.clearChat();
        ModelUserAccount currentUser = chatTitle.getUser();
        if (currentUser == null) {
            // Xử lý nếu chatTitle.getUser() trả về null
            return;
        }

        for (ModelReceiveMessage message : liChatHistory) {
            System.out.println(".updateChatHistoryList(): " + message.getFromUserID());
            if (currentUser.getUserID() == message.getFromUserID()) {
                
                
                chatBody.addItemLeft(message, sourceLang, targetLang);
                
            } else {
                chatBody.addItemRight(message);
            }
        }
        
    }
    
    private void updateOriChatHistoryList() {
        chatBody.clearChat();
        ModelUserAccount currentUser = chatTitle.getUser();
        if (currentUser == null) {
            // Xử lý nếu chatTitle.getUser() trả về null
            return;
        }

        for (ModelReceiveMessage message : liChatHistory) {
            System.out.println(".updateChatHistoryList(): " + message.getFromUserID());
            if (currentUser.getUserID() == message.getFromUserID()) {
                
                
                chatBody.addItemLeft(message);
                
            } else {
                chatBody.addItemRight(message);
            }
        }
        
    }
    


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
