package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import model.Model_Group;
import model.Model_Message;
import model.Model_User;

public class ServiceGroup {
    private final Connection con;
    private int user_Id;

    private final String INSERT_GROUP = "INSERT INTO `group` (groupName) VALUES (?)";
    private final String INSERT_GROUP_CONTACT = "INSERT INTO group_contact (groupId, user_Id) VALUES (?,?)";
    private final String SELECT_USER = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account where userName=?";
    private final String SELECT_MEMBER = "select user_account.User_ID, UserName,fullName, Email, Phone, Address, Avatar_path FROM user_account JOIN group_contact ON user_account.User_Id=group_contact.user_Id WHERE groupId=?";
    private final String SELECT_GROUP = "SELECT `group`.groupId, `group`.groupName FROM `group` JOIN group_contact ON `group`.groupId = group_contact.groupId WHERE group_contact.user_Id=?";
    
    public ServiceGroup(int user_Id) {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.user_Id = user_Id;
    }    
    
    public Model_Group addGroup(Model_Group group) {
    	try {
    		PreparedStatement p = con.prepareStatement(INSERT_GROUP, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, group.getGroupName());
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int groupId = r.getInt(1);
            group.setGroupId(groupId);
            r.close();
            p.close();

            p = con.prepareStatement(INSERT_GROUP_CONTACT);
            p.setInt(1, groupId);
            p.setInt(2, user_Id);
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return group;
    }
 
    public Model_User addMember(String user, int groupId){
    	try {
                Model_User account = null;
                int userMemberID = -1;
                PreparedStatement p = con.prepareStatement(SELECT_USER);
                p.setString(1, user);
                ResultSet r = p.executeQuery();
                
                if (r.next()) {
                	userMemberID = r.getInt(1);
                    String userName = r.getString(2);
                    String fullName = r.getString(3);
                    String email = r.getString(4);
                    String phone = r.getString(5);
                    String address = r.getString(6);
                    String avatar_path = r.getString(7);
                    account = new Model_User(userMemberID, userName, fullName, email, phone, address, avatar_path, false);
                }
                r.close();
                p.close();
                
                p = con.prepareStatement(INSERT_GROUP_CONTACT);
                p.setInt(1, groupId);
                p.setInt(2, userMemberID);
                p.execute();
                p.close();
                
                return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Model_User> getMember(int groupId) {
        List<Model_User> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_MEMBER);
            p.setInt(1, groupId);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int userID = r.getInt(1);
                String userName = r.getString(2);
                String fullName = r.getString(3);
                String email = r.getString(4);
                String phone = r.getString(5);
                String address = r.getString(6);
                String avatar_path = r.getString(7);
                list.add(new Model_User(userID, userName, fullName, email, phone, address, avatar_path, false));
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public List<Model_Group> getGroup(int User_Id) {
        List<Model_Group> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_GROUP);
            p.setInt(1, User_Id);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int groupId = r.getInt(1);
            	String groupName = r.getString(2);
            	Model_Group project = new Model_Group(groupId, groupName);
            	list.add(project);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
}
