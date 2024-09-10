package model;

import org.json.JSONObject;

public class Model_File {
    private int fromUserID;
    private int toUserID;
	private String name;
	private byte[] content;
	
	public Model_File(int fromUserID, int toUserID, String name, byte[] content) {
		this.name = name;
		this.content = content;
		this.fromUserID = fromUserID;
		this.toUserID = toUserID;
	}
	
	public Model_File(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	fromUserID = obj.getInt("fromUserID");
        	toUserID = obj.getInt("toUserID");
        	name = obj.getString("name");
        	content = convertHexStringToByteArray(obj.getString("content"));
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
			json.put("name", name);
			json.put("content", convertByteArrayToHexString(content));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    private String convertByteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private byte[] convertHexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
}
