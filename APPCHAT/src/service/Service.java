package service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Model_Group;
import model.Model_Message;
import model.Model_Message_Group;
import model.Model_User;
import view.Main;
import view.form.Login;

public class Service {
	private static Service instance;
	private Socket client;
	private final int PORT_NUMBER = 1610;
	private final String IP = "localhost";
	private Model_User user;
	private BufferedReader in;
	private DataOutputStream out;
	private Main main;
	
	public static Service getInstance(Main main) {
		if(instance == null) {
			instance = new Service(main);
		}
		return instance;
	}
	
    public static Service getInstance() {
    	return instance;
    }
	
	private Service(Main main) {
		this.main = main;
	}
	
    public void startClient(){
    	try {
        	client = new Socket(IP, PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(client.getInputStream() , StandardCharsets.UTF_8));
            out = new DataOutputStream(client.getOutputStream());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
        new Thread(() -> {
            while (true) {
                try {
//                    String message = in.readLine(); 
                    String message;
                    synchronized (in) {
                        message = in.readLine();
                    }
                    
                    if (message != null) {
                        System.out.println("client: " + message + "\n");
                        listen(message);
                    } else {
                        System.out.println("Client disconnected");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();    
    }
    
    public void listen(String newdata) {
    	JSONObject jsonData;
    	String data=new String(newdata);
		try {
			jsonData = new JSONObject(data);
	    	if(jsonData.getString("type").equals("register")) {
	    		main.getLogin().checkRegister(jsonData.getBoolean("check"));
	    	}
	    	else if(jsonData.getString("type").equals("login")) {
	    		main.getLogin().checkLogin(jsonData.getBoolean("check"));
	    	}
	    	else if(jsonData.getString("type").equals("loadLogin")) {
	            user = new Model_User(jsonData); 
	            setUser(user);
	    	}
	    	else if(jsonData.getString("type").equals("list_user")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_User list_user = new Model_User(json);
		    		if(!list_user.getUserName().equals(user.getUserName())) {
		    			main.getHome().getMenu_Left().newUser(list_user);
		    		}	            
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("active")) {
	    		int id = jsonData.getInt("userId");
	    		main.getHome().loadActive(id, true);
	    	}	    	
	    	else if(jsonData.getString("type").equals("noActive")) {
	    		int id = jsonData.getInt("userId");
	    		main.getHome().loadActive(id, false);
	    	}
	    	else if(jsonData.getString("type").equals("receiveMessage")) {
	    		System.out.println("nhan tin nhan toi");
                Model_Message message = new Model_Message(jsonData);
                main.getHome().getChat().getChatBody().addItemLeft(message);
	    	}
	    	else if(jsonData.getString("type").equals("historyMessage")) {
                String history = jsonData.getString("history");
                main.getHome().getChat().getChatBody().loadHistory(history);
	    	}
	    	else if(jsonData.getString("type").equals("addGroup")) {
	    		Model_Group group = new Model_Group(jsonData);
	    		main.getHome().getMenu_Left().addGroup(group);
//                main.getHome().getChat().getChatBody().loadHistory(history);
	    	}
	    	else if(jsonData.getString("type").equals("addMember")) {
	    		Model_User user = new Model_User(jsonData);
	    		main.getHome().getChat().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("listMember")) {
	    		Model_User user = new Model_User(jsonData);
	    		main.getHome().getChat().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("listGroup")) {	    	
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Group group = new Model_Group(json);
	                main.getHome().getMenu_Left().addGroup(group);
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("sendMessageGroup")) {   				
    			if(main.getHome().getChat().getGroup().getGroupId() == jsonData.getInt("groupId") && main.getHome().getChat().getGroup().getGroupName().equals(main.getHome().getChat().getChatTitle().getLbName().getText())) {
    				Model_Message_Group message = new Model_Message_Group(jsonData);
    				main.getHome().getChat().getChatBody().addItemLeft(message);
    			}
	    	}
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void login(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void register(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void registerInfo(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void sendMessage(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void sendMessageGroup(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void historyMessage(int user_Id2) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "historyMessage");
			json.put("user_Id2", user_Id2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listGroup() {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listGroup");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addGroup(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addMember(String userName, Model_Group group) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "addMember");
			json.put("userName", userName);
			json.put("groupId", group.getGroupId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listMember(int groupId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listMember");
			json.put("groupId", groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    
    public void selectedUser(Model_User user) {
    	main.getHome().setUser(user);
    }
    
    public void selectedGroup(Model_Group group) {
    	main.getHome().setGroup(group);
    }
    
    public void sendBottomChat(Model_Message message) {
    	main.getHome().getChat().getChatBody().addItemRight(message);
    }
    
    public void sendBottomChat(Model_Message_Group message) {
    	main.getHome().getChat().getChatBody().addItemRight(message);
    }

	public Model_User getUser() {
		return user;
	}

	public void setUser(Model_User user) {
		this.user = user;
	}

	public Socket getClient() {
		return client;
	}

	public Main getMain() {
		return main;
	}
	
	
    
    
}
