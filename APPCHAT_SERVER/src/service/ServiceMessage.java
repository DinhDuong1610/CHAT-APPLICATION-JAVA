package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DatabaseConnection;
import model.Model_Message;

public class ServiceMessage {
    private final Connection con;
    private int user_Id;
    private final String SELECT_HISTORY = "SELECT history FROM history_message WHERE (user1=? and user2=?) or (user1=? and user2=?)";
    private final String UPDATE_HISTORY = "UPDATE history_message set history=? WHERE (user1=? and user2=?) or (user1=? and user2=?)";
    private final String INSERT_MESSAGE = "INSERT INTO history_message (user1, user2, history) VALUES (?, ?, '')";
    
    public ServiceMessage(int user_Id) {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.user_Id = user_Id;
    }
    
    public void sendMessage(Model_Message message) {
    	String history = "";
    	boolean check = false;
    	try {
            PreparedStatement p = con.prepareStatement(SELECT_HISTORY);
            p.setInt(1, message.getFromUserID());
            p.setInt(2, message.getToUserID());
            p.setInt(3, message.getToUserID());
            p.setInt(4, message.getFromUserID());
            ResultSet r = p.executeQuery();
            if (r.next()) {
            	check = true;
            	history = r.getString(1);
            }
            r.close();
            p.close();
            
            if(check == true) {
            	StringBuilder sb = new StringBuilder(history);
                sb.append("\n" + message.getFromUserID() + "|" + message.getText() + "|" + message.getToUserID() + "|" + message.getTime());
                history = sb.toString();
                
                p = con.prepareStatement(UPDATE_HISTORY);
                p.setString(1, history);
                p.setInt(2, message.getFromUserID());
                p.setInt(3, message.getToUserID());
                p.setInt(4, message.getToUserID());
                p.setInt(5, message.getFromUserID());
                p.execute();
                p.close();
            }
            else {
            	p = con.prepareStatement(INSERT_MESSAGE);
                p.setInt(1, message.getFromUserID());
                p.setInt(2, message.getToUserID());
                p.execute();
                p.close();
                
            	StringBuilder sb = new StringBuilder(history);
                sb.append("\n" + message.getFromUserID() + "|" + message.getText() + "|" + message.getToUserID() + "|" + message.getTime());
                history = sb.toString();
                
                p = con.prepareStatement(UPDATE_HISTORY);
                p.setString(1, history);
                p.setInt(2, message.getFromUserID());
                p.setInt(3, message.getToUserID());
                p.setInt(4, message.getToUserID());
                p.setInt(5, message.getFromUserID());
                p.execute();
                p.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public String historyMessage(int user_Id2) {
		String history = "";
        try {
            PreparedStatement p = con.prepareStatement(SELECT_HISTORY);
            p.setInt(1, user_Id);
            p.setInt(2, user_Id2);
            p.setInt(3, user_Id2);
            p.setInt(4, user_Id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
            	history = r.getString(1);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return history;
	}
}
