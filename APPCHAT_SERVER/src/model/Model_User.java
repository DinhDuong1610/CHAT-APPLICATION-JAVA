package model;

import org.json.JSONObject;

public class Model_User {
	private int user_Id;
	private String userName;
	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String avatar_path;
	private boolean status;
	
	public Model_User(int user_Id, String userName, String fullName, String email, String phone,
			String address, String avatar_path, boolean status) {
		this.user_Id = user_Id;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.avatar_path = avatar_path;
		this.status = status;
	}
	
    public Model_User(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            user_Id = obj.getInt("user_Id");
            userName = obj.getString("userName");
            fullName = obj.getString("fullName");
            email = obj.getString("email");
            phone = obj.getString("phone");
            address = obj.getString("address");
            avatar_path = obj.getString("avatar_path");
            status = obj.getBoolean("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("user_Id", user_Id);
			json.put("userName", userName);
			json.put("fullName", fullName);
			json.put("email", email);
			json.put("phone", phone);
			json.put("address", address);
			json.put("avatar_path", avatar_path);
			json.put("status", status);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
       
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvatar_path() {
		return avatar_path;
	}
	public void setAvatar_path(String avatar_path) {
		this.avatar_path = avatar_path;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
