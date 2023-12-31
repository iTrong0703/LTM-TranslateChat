/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import java.awt.Color;
import javax.swing.Icon;

/**
 *
 * @author TrongFlorida
 */
public class MessageRight extends javax.swing.JLayeredPane {

    /**
     * Creates new form MessageLeft
     */
    public MessageRight() {
        initComponents();
        txt.setBackground(new Color(179, 233, 255));// set color chat bên phải
    }
    
    
    public void setMessageRightText(String text) {
        if(text.equals("")) {
            txt.hideText();
        }else {
            txt.setMessageText(text);
        }
        txt.seen();
    }

    public void setMessageRightImage(Icon... image) {
        txt.setMessageImage(true, image);
    }
    
    public void setMessageRightImage(String... image) {
        txt.setMessageImage(false, image);
    }
    
    public void setMessageRightFile(String fileName, String fileSize) {
        txt.setMessageFile(fileName, fileSize);  
    }
    
    public void setMessageRightTime() {
        txt.setMessageTime("5:10 PM");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new component.ChatItem();

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ChatItem txt;
    // End of variables declaration//GEN-END:variables
}
