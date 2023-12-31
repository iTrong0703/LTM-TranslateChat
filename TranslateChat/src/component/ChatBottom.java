/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import event.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.ModelSendMessage;
import model.ModelUserAccount;
import net.miginfocom.swing.MigLayout;
import service.Service;
import swing.JIMSendTextPane;
import swing.ScrollBar;

/**
 *
 * @author TrongFlorida
 */
public class ChatBottom extends javax.swing.JPanel {
    
    private ModelUserAccount user;

    public ModelUserAccount getUser() {
        return user;
    }

    public void setUser(ModelUserAccount user) {
        this.user = user;
    }

    public ChatBottom() {
        initComponents();
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                refresh();
            }
            
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setHintText("Hãy nhập tin nhắn...");//Hiện chữ trong ô input
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(242, 242, 242));
        sb.setPreferredSize(new Dimension(2, 10));// custom lại thanh scroll bar
        scroll.setVerticalScrollBar(sb); 
//        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/icon/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txt.getText().trim();
                if(!text.equals("")) {
                    ModelSendMessage message = new ModelSendMessage(Service.getInstance().getUser().getUserID(), user.getUserID(), text);
                    send(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                   
                }
            }
            
        });
        panel.add(cmd);
        add(panel);
    }
    
    private void send(ModelSendMessage data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    private void refresh() {
        revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
