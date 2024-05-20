package model;

import org.json.JSONObject;

public class Model_Login {
	String userName;
	String password;		
	
	public Model_Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
    public Model_Login(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	userName = obj.getString("userName");
        	password = obj.getString("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("userName", userName);
			json.put("password", password);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
