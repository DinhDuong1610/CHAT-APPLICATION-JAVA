package model;

import org.json.JSONObject;

public class Model_Group {
	private int groupId;
	private String groupName;
	
	public Model_Group(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}
	
    public Model_Group(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	groupId = obj.getInt("groupId");
        	groupName = obj.getString("groupName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject(String type) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", type);
            json.put("groupId", groupId);
            json.put("groupName", groupName);
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
