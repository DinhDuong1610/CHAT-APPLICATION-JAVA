package model;

import org.json.JSONObject;

public class Model_Message {
    private int fromUserID;
    private int toUserID;
    private String text;
    private String time;    
    
	public Model_Message(int fromUserID, int toUserID, String text, String time) {
		this.fromUserID = fromUserID;
		this.toUserID = toUserID;
		this.text = text;
		this.time = time;
	}

	public Model_Message() {
    }
	
    public Model_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	fromUserID = obj.getInt("fromUserID");
        	toUserID = obj.getInt("toUserID");
        	text = obj.getString("text");
        	time = obj.getString("time");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject(String type) {
        try {
            JSONObject json = new JSONObject();
            json.put("type", type);
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("text", text);
            json.put("time", time);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    

}
