/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author TrongFlorida
 */
public class MenuButton extends JButton{

    public Icon getIconNotSelected() {
        return iconNotSelected;
    }

    public void setIconNotSelected(Icon iconNotSelected) {
        this.iconNotSelected = iconNotSelected;
    }

    public Icon getIconSelected() {
        return iconSelected;
    }

    public void setIconSelected(Icon iconSelected) {
        this.iconSelected = iconSelected;
    }
    
    private Icon iconNotSelected;
    private Icon iconSelected;
    
    public MenuButton() {
        setContentAreaFilled(false);// Tạp ra các nút trong suốt
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));//cursor pointer
    }

    // Đổi màu khi click chọn icon
    @Override
    public void setSelected(boolean b) {
        super.setSelected(b); 
        if(b) {
            setIcon(iconSelected);
        } else {
            setIcon(iconNotSelected);
        }
    }

    //Thêm dấu gạch chân bên dưới icon menu
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if(isSelected()) {
            g.setColor(new Color(110, 213, 255));
            g.fillRect(0, getHeight() - 3, getWidth(), getHeight());
        }
    }
    
    
}
