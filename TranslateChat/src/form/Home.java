/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import model.ModelUserAccount;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author TrongFlorida
 */
public class Home extends javax.swing.JLayeredPane {

    private ChatBox chat; 
    
    
    public Home() {
        initComponents();
        init();
    }

    private void init() {
        //Rang buoc left la 200 right la 200 con lai chiem het
        setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        this.add(new MenuLeft());
        chat = new ChatBox();
        this.add(chat);
        this.add(new MenuRight());
        chat.setVisible(false);
    }
    
    
    public void setUser(ModelUserAccount user) {
        chat.setUser(user);
        chat.setVisible(true); // hiển thị phần chat của user tương ứng
    }
    
    public void updateUser(ModelUserAccount user) {
        chat.updateUser(user);// update tên của user và chat lên giao diện
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
