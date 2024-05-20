package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Model_Login;
import model.Model_Message;
import model.Model_Register;
import model.Model_User;

public class Service {
    private static Service instance;
    private ServerSocket serverSocket;
    public JTextArea textArea;
    private final int PORT_NUMBER = 1610;
	private ServiceUser serviceUser;
	private ArrayList<ClientHandler> clients = new ArrayList<>();
	private ServiceMessage serviceMessage;
	private static int id = 1000000;
    
    public static Service getInstance(JTextArea textArea) {
        if (instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }
    
    public static Service getInstance() {
        return instance;
    }
    
    private Service(JTextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser(clients);
    }
    
    public void startServer() {
        new Thread(() -> {
            try {
            	serverSocket = new ServerSocket(PORT_NUMBER);
                textArea.append("Server has started on port: " + PORT_NUMBER + "\n");
                
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    textArea.append("One client connected\n");
                    
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream() , StandardCharsets.UTF_8));
                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                        ClientHandler clientHandler = new ClientHandler(++id,this, in, out, clients, clientSocket);
                    }
                    catch (Exception e) {
                    	clientSocket.close();
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public void listen(ClientHandler client, String newdata) {
        serviceMessage = new ServiceMessage(client.getUserId());
    	String data = new String(newdata);
		new Thread(()->{
    		try {
    			JSONObject jsonData = new JSONObject(data);
    			
    	    	if(jsonData.getString("type").equals("register")) {
    	            Model_Register register = new Model_Register(jsonData);
    	            boolean message = serviceUser.register(register);
    	            
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "register");
    	    			json.put("check", message);
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}    	            
    	            broadcast(client.getUserId(), json);
    	    	}
    			else if(jsonData.getString("type").equals("login")) {
    	            Model_Login login = new Model_Login(jsonData);
    	            boolean message = serviceUser.login(login);
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "login");
    	    			json.put("check", message);
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}    	                 	            
    	            broadcast(client.getUserId(), json);    	            
    	            if(message) {
        	            Model_User account = serviceUser.loadLogin();
        	            broadcast(client.getUserId(), account.toJsonObject("loadLogin"));
        	            client.setUserId(account.getUser_Id());
        	            
           	            List<Model_User> list = serviceUser.getUser();
        	            if(list.size() == 0) textArea.append("rong!!!!");
        	            JSONArray jsonArray = new JSONArray();
        	            for(Model_User user : list) { 
        	            	jsonArray.put(user.toJsonObject("list_user"));
        	            	textArea.append("list user :" +  user.toJsonObject("list_user") + "\n");
        	            }
        	            JSONObject json2 = new JSONObject();
        	            json2.put("type", "list_user");
        	            json2.put("jsonArray", jsonArray);
    	            	broadcast(client.getUserId(), json2);
    	            	
        	            JSONObject jsonActive = new JSONObject();
        	            jsonActive.put("type", "active");
        	            jsonActive.put("userId", account.getUser_Id());
        	            broadcastActive(client.getUserId(), jsonActive);
    	            }    	            
    	    	}
    	    	else if(jsonData.getString("type").equals("registerInfo")) {
    	    		Model_User user = new Model_User(jsonData);
    	    		serviceUser.registerInfo(user);
    	    	}
    			else if(jsonData.getString("type").equals("sendMessage")) {
    	            Model_Message sendMessage = new Model_Message(jsonData);
    	            serviceMessage.sendMessage(sendMessage);
    	            Model_Message receiveMessage = new Model_Message(jsonData);
    	            broadcast(jsonData.getInt("toUserID"), sendMessage.toJsonObject("receiveMessage"));    	 
    	            textArea.append("sendMessage: " + sendMessage.toJsonObject("receiveMessage"));
    			}
    			else if(jsonData.getString("type").equals("historyMessage")) {
    				int user_Id2 = jsonData.getInt("user_Id2");
	            	textArea.append("historyMessage :" +  client.getUserId() + " - " + user_Id2 +  "\n");
	            	String history = serviceMessage.historyMessage(user_Id2);
    	            broadcastHistory(client.getUserId(), history);    	            
    			}
    	    		
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}
    	}).start();
    }
    
    public synchronized void broadcast(int userId, JSONObject jsonData) {
            for (ClientHandler client : clients) {
                if(client.getUserId() == userId) {
                	client.sendMessage(jsonData);
                }
            }
    }
    
    public void broadcastActive(int userId, JSONObject jsonData) {
            for (ClientHandler client : clients) {
                if(client.getUserId() != userId) {
                	client.sendMessage(jsonData);
                }
            }
    }
    
    public void broadcastHistory(int userId, String history) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "historyMessage");
			json.put("history", history);
		} catch (Exception e) {
			e.printStackTrace();
		}
            for (ClientHandler client : clients) {
                if(client.getUserId() == userId) {
                	client.sendMessage(json);
                }
            }
    }
     

}
