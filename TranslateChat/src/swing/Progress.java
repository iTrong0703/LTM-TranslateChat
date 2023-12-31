/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import javax.swing.JProgressBar;

/**
 *
 * @author TrongFlorida
 */
public class Progress extends JProgressBar{
    
    private ProgressType progressType = ProgressType.NONE;

    public ProgressType getProgressType() {
        return progressType;
    }

    public void setProgressType(ProgressType progressType) {
        this.progressType = progressType;
        repaint();
    }
    
    public Progress() { // vòng tròn loading tải ảnh
        setOpaque(false);
        setUI(new ProgressCircleUI(this));
    }
    
    public static enum ProgressType { // enum là kiểu dữ liệu tự định nghĩa
        NONE, DOWNFILE, CANCEL, FILE //giúp giới hạn trong 4 kiểu là NON, DOWNFILE, CANCEL, FILE 
    }
}
