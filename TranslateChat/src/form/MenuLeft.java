/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import component.ItemPeople;
import event.IEventMenuLeft;
import event.PublicEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import model.ModelUserAccount;
import net.miginfocom.swing.MigLayout;
import service.Service;
import swing.ScrollBar;


public class MenuLeft extends javax.swing.JPanel {

    private List<ModelUserAccount> userAccount;
    private static ModelUserAccount selectedUser;
    private ChatBox chatBox;
    private static int currentUserId;

    public static int getCurrentUserId() {
        return currentUserId;
    }
    
    public static int getSelectedUserId() {
        return selectedUser.getUserID();
    }
   
    public MenuLeft() {
        initComponents();
        init();
    }
    
   

    private void init() {
        scrollList.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        userAccount = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new IEventMenuLeft() {
            @Override
            public void newUser(List<ModelUserAccount> users) {
                for (ModelUserAccount u : users) {
                    userAccount.add(u);
                    ItemPeople item = new ItemPeople(u);

                    // Thêm MouseListener để theo dõi sự kiện click
                    item.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            // Gọi phương thức để xử lý khi một người dùng được chọn
                            setSelectedUser(u);
                        }
                    });

                    menuList.add(item, "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void userConnect(int userID) {
                for(ModelUserAccount u : userAccount) {
                    if(u.getUserID() == userID) {
                        u.setStatus(true);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if(menuMessage.isSelected()) {
                    for(Component com : menuList.getComponents()) {
                        ItemPeople item = (ItemPeople) com;
                        if(item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    } 
                }
            }

            @Override
            public void userDisconnect(int userID) {
                for(ModelUserAccount u : userAccount) {
                    if(u.getUserID() == userID) {
                        u.setStatus(false);
                        PublicEvent.getInstance().getEventMain().updateUser(u);//update lại user status
                        break;
                    }
                }
                if(menuMessage.isSelected()) {
                    for(Component com : menuList.getComponents()) {
                        ItemPeople item = (ItemPeople) com;
                        if(item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    } 
                }
            }

            @Override
            public void userLoggedIn(int userID) {
                // Gọi phương thức để xử lý khi một người dùng đăng nhập thành công
                currentUserId = userID;
            }
            
        });
        showMessage();
    }
    
    // Hiện người chat vào list
    private void showMessage() {
        //test
        menuList.removeAll();// Xóa list cũ đã lọc
        for(ModelUserAccount u : userAccount) {
            menuList.add(new ItemPeople(null), "wrap");
        }
        refreshMenuList();
    }
    
    public void showGroup() {
        //test
        menuList.removeAll();// Xóa list cũ đã lọc
        for(int i=0; i<10; i++) {
            menuList.add(new ItemPeople(null), "wrap");
        }
        refreshMenuList();
    }
    
    public void showBox() {
        //test
        menuList.removeAll();// Xóa list cũ đã lọc
        for(int i=0; i<5; i++) {
            menuList.add(new ItemPeople(null), "wrap");
        }
        refreshMenuList();
    }
    
    public void setSelectedUser(ModelUserAccount user) {
        this.selectedUser = user;
        
        // Yêu cầu lịch sử chat giữa người dùng hiện tại và người được chọn
        Service.getInstance().requestChatHistory(currentUserId, selectedUser.getUserID());

    }

    
    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new component.MenuButton();
        menuGroup = new component.MenuButton();
        menuBox = new component.MenuButton();
        scrollList = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setIconNotSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/message.png"))); // NOI18N
        menuMessage.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/message_selected.png"))); // NOI18N
        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/group.png"))); // NOI18N
        menuGroup.setIconNotSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/group.png"))); // NOI18N
        menuGroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/group_selected.png"))); // NOI18N
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/box.png"))); // NOI18N
        menuBox.setIconNotSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/box.png"))); // NOI18N
        menuBox.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/icon/box_selected.png"))); // NOI18N
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });
        menu.add(menuBox);

        scrollList.setBackground(new java.awt.Color(242, 242, 242));
        scrollList.setBorder(null);
        scrollList.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.setToolTipText("");

        menuList.setBackground(new java.awt.Color(242, 242, 242));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        scrollList.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollList)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollList)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if(!menuMessage.isSelected()) {// Nếu nút đã chọn rồi thì khối mã bên trong sẽ không được thực hiện lại
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false);
            showMessage();
        }       
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        if(!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
        }
        
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if(!menuBox.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(false);
            menuBox.setSelected(true);
            showBox();
        }
        
    }//GEN-LAST:event_menuBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private component.MenuButton menuBox;
    private component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuList;
    private component.MenuButton menuMessage;
    private javax.swing.JScrollPane scrollList;
    // End of variables declaration//GEN-END:variables
}
