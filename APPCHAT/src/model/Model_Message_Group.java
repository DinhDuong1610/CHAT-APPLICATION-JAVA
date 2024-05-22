package model;

import org.json.JSONObject;

public class Model_Message_Group {
	private int groupId;
	private String name;
	private String message;	
	
    public Model_Message_Group(int groupId, String name, String message) {
		this.groupId = groupId;
		this.name = name;
		this.message = message;
	}

	public Model_Message_Group(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	groupId = obj.getInt("groupId");
            name = obj.getString("name");
            message = obj.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", type);
            json.put("groupId", groupId);
            json.put("name", name);
            json.put("message", message);
            return json;
        } catch (Exception e) {
            return null;
        }
    }
	
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String messsage) {
		this.message = messsage;
	}
	
	
}
