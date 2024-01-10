    
package component;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import event.IEventMenuRight;
import event.PublicEvent;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.Icon;
import javax.swing.JScrollBar;
import model.ModelReceiveMessage;
import model.ModelSendMessage;
import net.miginfocom.swing.MigLayout;
import swing.ScrollBar;


public class ChatBody extends javax.swing.JPanel {

    /**
     * Creates new form ChatBody
     */
    public ChatBody() {
        initComponents();
        init();
//        addItemRight("Hello sir !When I try to add the JIMSendTextPane class to the Panel \"Chat_Item\" a Jscrol is added automatically !Is there any solution to that ?", new ImageIcon(getClass().getResource("/icon/testing/chon.jpg")), new ImageIcon(getClass().getResource("/icon/testing/qbv.png")));
//        addItemLeft("Hello sir !\n" +
//                    "When I try to add the JIMSendTextPane class to the Panel \"Chat_Item\" a Jscrol is added automatically !\n" +
//                    "Is there any solution to that ?", "Trong", new ImageIcon(getClass().getResource("/icon/testing/chon.jpg")), new ImageIcon(getClass().getResource("/icon/testing/qbv.png")));
//        addChatDate("08/12/2023");
//        String img[] = {"UXNTwUoei{WXWBj@offQt5fk~qjZ-oazM{j["};
//        addItemRight("Hello sir !When I try to add the JIMSendTextPane class to the Panel \"Chat_Item\" a Jscrol is added automatically !Is there any solution to that ?", new ImageIcon(getClass().getResource("/icon/testing/qbv.png")));
//        addItemLeft("Hello", "Trong", new ImageIcon(getClass().getResource("/icon/testing/pic.jpg")), new ImageIcon(getClass().getResource("/icon/testing/chon.jpg")));
//        addChatDate("Today");
//        addItemRight("Hello sir !When I try to add the JIMSendTextPane class to the Panel \"Chat_Item\" a Jscrol is added automatically !Is there any solution to that ?");
//        addItemLeft("", "Trong", img);
//        addItemFileLeft("", "Kha", "exam1.pdf", "1 MB");
//        addItemFileRight("", "mysolution.pdf", "3 MB");
        
    }
    
    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));// lắp đầy chiều ngang, chiều dọc các tin nhắn cách nhau 5
        scrollChat.setVerticalScrollBar(new ScrollBar()); // ScrollBar đã tạo ở file swing 
        scrollChat.getVerticalScrollBar().setBackground(Color.WHITE);
//        PublicEvent.getInstance().addEventMenuRight(new IEventMenuRight() {
//            @Override
//            public void translateText(String sourceLang, String targetLang) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//
//            @Override
//            public void displayOriginalText() {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//            }
//        });
    }
    
    public void addItemLeft(ModelReceiveMessage data){
        MessageLeft item = new MessageLeft();
        item.setMessageLeftText(data.getText());
        item.setMessageLeftTime();
        body.add(item, "wrap, w 100::80%"); // giới hạn kích thước box chứa tin nhắn là 80% box cha, nếu quá thì xuống dòng
        repaint();
        revalidate();
    }
    
    public void addItemLeft(ModelReceiveMessage data, String sourceLang, String targetLang) throws IOException, LangDetectException{
        MessageLeft item = new MessageLeft();
        
        if(sourceLang == "auto") {
            item.setMessageLeftText(translate(detectLanguage(data.getText()), targetLang, data.getText()));
            System.out.println("Tự động phát hiện: "+ detectLanguage(data.getText()));
        } else if (sourceLang == "none" || targetLang == "none"){
            item.setMessageLeftText(data.getText());
        } else {
            item.setMessageLeftText(translate(sourceLang, targetLang, data.getText()));
        }
        
        item.setMessageLeftTime();
        body.add(item, "wrap, w 100::80%"); // giới hạn kích thước box chứa tin nhắn là 80% box cha, nếu quá thì xuống dòng
        repaint();
        revalidate();
    }
    
    public void addItemRight(ModelReceiveMessage data) {
        MessageRight item = new MessageRight();
        item.setMessageRightText(data.getText());
        item.setMessageRightTime();
        body.add(item, "wrap, al right, w 100::80%"); // al right cho tin nhắn về phía bên phải, giới hạn kích thước cố định là 100 và tự động là 80%
        repaint();
        revalidate();
    }
    
    public void addItemLeft(String text, String user, String[] image) {
        MessageLeftWithProfile item = new MessageLeftWithProfile();
        item.setMessageLeftText(text);
        item.setMessageLeftImage(image);
        item.setMessageLeftTime();
        item.setMessageUserProfile(user);
        body.add(item, "wrap, w 100::80%"); // giới hạn kích thước box chứa tin nhắn là 80% box cha, nếu quá thì xuống dòng
        body.repaint();
        body.revalidate();
    }
    
    public void addItemFileLeft(String text, String user, String fileName, String fileSize) {
        MessageLeftWithProfile item = new MessageLeftWithProfile();
        item.setMessageLeftText(text);
        item.setMessageLeftFile(fileName, fileSize);
        item.setMessageLeftTime();
        item.setMessageUserProfile(user);
        body.add(item, "wrap, w 100::80%"); // giới hạn kích thước box chứa tin nhắn là 80% box cha, nếu quá thì xuống dòng
        body.repaint();
        body.revalidate();
    }
    
    public void addItemRight(ModelSendMessage data) {
        MessageRight item = new MessageRight();
        item.setMessageRightText(data.getText());
        body.add(item, "wrap, al right, w 100::80%"); // al right cho tin nhắn về phía bên phải, giới hạn kích thước cố định là 100 và tự động là 80%
        
        repaint();//refresh giao diện
        revalidate();
        item.setMessageRightTime();
        scrollToBottom();
    }
    
    public void addItemFileRight(String text, String fileName, String fileSize) {
        MessageRight item = new MessageRight();
        item.setMessageRightText(text);
        item.setMessageRightFile(fileName, fileSize);
        body.add(item, "wrap, al right, w 100::80%"); // al right cho tin nhắn về phía bên phải, giới hạn kích thước cố định là 100 và tự động là 80%
        
        body.repaint();//refresh giao diện
        body.revalidate();
    }
    
    public void addChatDate(String date) {
        ChatDate d = new ChatDate();
        d.setChatDate(date);
        body.add(d, "wrap, al center");
        body.repaint();//refresh giao diện
        body.revalidate();
    }

    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    
    // phát hiện ngôn ngữ
    private static String detectLanguage(String text) throws LangDetectException {
        // Load ngôn ngữ profiles từ thư mục profiles
        try {
            DetectorFactory.loadProfile("profiles");
        } catch (LangDetectException e) {
            e.printStackTrace();
        }

        // Tạo đối tượng Detector
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbx2y0sHJjVhbyH5CFxVi6OE0pdQ9VzJUAcpArgZlJHJaO3irGQR8KWnfmlx_qmW5giJrw/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollChat = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        scrollChat.setBorder(null);
        scrollChat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 985, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        scrollChat.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollChat)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollChat)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Luôn cuộn thanh cuộn xuống cuối cùng của đoạn chat
    private void scrollToBottom() {
        JScrollBar verticalBar = scrollChat.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane scrollChat;
    // End of variables declaration//GEN-END:variables
}
