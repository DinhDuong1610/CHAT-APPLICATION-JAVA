package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import model.Model_Login;
import model.Model_Register;
import model.Model_User;

public class ServiceUser {
	private ArrayList<ClientHandler> clients;
    private final Connection con;
    private int user_Id;
    
    private final String CHECK_USER = "select User_ID from user where UserName=?";
    private final String INSERT_USER = "insert into user (UserName, Password) values (?,?)";
    private final String CHECK_LOGIN = "select User_ID from user where userName=? and password=?";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (User_ID, UserName, fullName, Email, Phone, Address, Avatar_path, status) values (? ,? , ?, ?, ?, ?, ?, 1)";
    private final String LOGIN = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account where user_Id=?";
    private final String SELECT_USER_ACCOUNT = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account"; // limit 3

    
    public ServiceUser(ArrayList<ClientHandler> clients) {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.clients = clients;
        user_Id = 0;
    }
    
    public boolean register(Model_Register data) {
    	boolean check = false;
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                check = false;
            } else {
                check = true;
            }
            r.close();
            p.close();
            if (check) {
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                user_Id = r.getInt(1);
                r.close();
                p.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public boolean login(Model_Login data) {
    	boolean check = false;
        try {
            PreparedStatement p = con.prepareStatement(CHECK_LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, data.getUserName());
            p.setString(2, data.getPassword());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                check = true;
                user_Id = r.getInt(1);
            } else {
                check = false;
            }
            r.close();
            p.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public void registerInfo(Model_User data) {
        try {
          PreparedStatement p = con.prepareStatement(INSERT_USER_ACCOUNT);
          p.setInt(1, user_Id);
          p.setString(2, data.getUserName());
          p.setString(3, data.getFullName());
          p.setString(4, data.getEmail());
          p.setString(5, data.getPhone());
          p.setString(6, data.getAddress());
          p.setString(7, data.getAvatar_path());
          p.execute();
          p.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public Model_User loadLogin(){
    	try {
    		if(user_Id != 0) {
                Model_User data = null;
                PreparedStatement p = con.prepareStatement(LOGIN);
                p.setInt(1, user_Id);
                ResultSet r = p.executeQuery();
                if (r.next()) {
                    int userID = r.getInt(1);
                    String userName = r.getString(2);
                    String fullName = r.getString(3);
                    String email = r.getString(4);
                    String phone = r.getString(5);
                    String address = r.getString(6);
                    String avatar_path = r.getString(7);
                    data = new Model_User(userID, userName, fullName, email, phone, address, avatar_path, true);
                    return data;
                }
                r.close();
                p.close();
                return data;
    		}
    		else {
    			return null;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Model_User> getUser() {
        List<Model_User> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int userID = r.getInt(1);
                String userName = r.getString(2);
                String fullName = r.getString(3);
                String email = r.getString(4);
                String phone = r.getString(5);
                String address = r.getString(6);
                String avatar_path = r.getString(7);
                boolean status = false;
                if(isActive(userID)) {
                	status = true;
                }                
                list.add(new Model_User(userID, userName, fullName, email, phone, address, avatar_path, status));
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public boolean isActive(int userId) {
        for (ClientHandler client : clients) {
            if (client.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
    
}
