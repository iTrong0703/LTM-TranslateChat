
package service;

import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ModelClient;
import model.ModelLogin;
import model.ModelMessage;
import model.ModelReceiveMessage;
import model.ModelRegister;
import model.ModelSendMessage;
import model.ModelUserAccount;

public class ServiceUser {

    public ServiceUser() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    public ModelMessage register(ModelRegister data) {
        //Kiểm tra user đó có tồn tại chưa
        ModelMessage message = new ModelMessage();

        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("User Already Exit");// nếu trong databse có username này r thì cảnh báo
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();
            if (message.isAction()) {
                // Insert user register
                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                int userID = r.getInt(1);
                r.close();
                p.close();
                //Tạo tài khoản
                p = con.prepareStatement(INSERT_USER_ACCOUNT);
                p.setInt(1, userID);
                p.setString(2, data.getUserName());
                p.execute();
                p.close();
                con.commit();
                con.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Successfull"); // hiện thông báo dky thành công
                message.setData(new ModelUserAccount(userID, data.getUserName(), "", "", true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message.setAction(false);
            message.setMessage("Server Error");
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e2) {
            }
        }
        return message;

    }

    public ModelUserAccount login(ModelLogin login) throws SQLException {
        ModelUserAccount data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        
        System.out.println(login.getUserName());
        System.out.println(login.getPassword());
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.first()) {
//            System.out.println("service.ServiceUser.login()");
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            data = new ModelUserAccount(userID, userName, gender, image, true);
        }
        r.close();
        p.close();
        return data;
    }

    public List<ModelUserAccount> getUser(int exitUser) throws SQLException {
        List<ModelUserAccount> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            list.add(new ModelUserAccount(userID, userName, gender, image, checkUserStatus(userID)));
        }
        r.close();
        p.close();
        return list;
    }
    
    private boolean checkUserStatus(int userID) {
        List<ModelClient> clients = Service.getInstance(null).getListClient();
        for(ModelClient c : clients) {
            if(c.getUser().getUserID() == userID) {
                return true;
            }
        }
        return false;
    }
    
    public void saveChatHistory(ModelSendMessage data) {
        try {
            // Insert the message into chat_history
            PreparedStatement p = con.prepareStatement(INSERT_CHAT_HISTORY);
            p.setInt(1, data.getFromUserID());
            p.setInt(2, data.getToUserID());
            p.setString(3, data.getText());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your application's requirements
        }
    }
    
    public List<ModelReceiveMessage> getChatHistory(int fromUserID, int toUserID) {
        List<ModelReceiveMessage> chatHistory = new ArrayList<>();
        try {
//            System.out.println("fromUserID: " + fromUserID);
//            System.out.println("toUserID: " + toUserID);
//            System.out.println("service.ServiceUser.getChatHistory()");
            PreparedStatement p = con.prepareStatement(SELECT_CHAT_HISTORY);
            p.setInt(1, fromUserID);
            p.setInt(2, toUserID);
            p.setInt(3, toUserID);  // Thêm dòng này
            p.setInt(4, fromUserID);  // Thêm dòng này  
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int chatID = r.getInt("ChatID");
                int senderID = r.getInt("FromUserID");
                int receiverID = r.getInt("ToUserID");
                String messageText = r.getString("Message");
                ModelReceiveMessage message = new ModelReceiveMessage(chatID, senderID, receiverID, messageText);
                chatHistory.add(message);
            }
            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        return chatHistory;
    }


    //Sql
    private final String SELECT_CHAT_HISTORY = "SELECT ChatID, FromUserID, ToUserID, Message, Timestamp FROM chat_history WHERE (FromUserID = ? AND ToUserID = ?) OR (FromUserID = ? AND ToUserID = ?) ORDER BY Timestamp";

    

    // SQL statement to insert registration message into chat_history
    private final String INSERT_CHAT_HISTORY = "INSERT INTO chat_history (FromUserID, ToUserID, Message) VALUES (?, ?, ?)";
    private final String LOGIN = "select UserID, user_account.UserName, Gender, ImageString from user join user_account using (UserID) where user.UserName=BINARY(?) and user.`Password`=BINARY(?) and user_account.`Status`='1'";
    private final String SELECT_USER_ACCOUNT = "SELECT UserID, UserName, Gender, ImageString FROM user_account WHERE user_account.`Status`='1' AND UserID<>?";
    private final String INSERT_USER = "INSERT INTO user (UserName, `Password`) VALUES (?,?)"; // Dùng dấu `` vì trong pass hay có kí tự đặc biệt
    private final String INSERT_USER_ACCOUNT = "INSERT INTO user_account (UserID, UserName) VALUES (?,?)";
    private final String CHECK_USER = "SELECT UserID FROM user WHERE  UserName =? LIMIT 1";

    // Instance
    private final Connection con;
}
