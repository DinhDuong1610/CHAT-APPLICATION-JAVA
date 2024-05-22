package service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONObject;

public class ClientHandler extends Thread{
    private BufferedReader in;
    private DataOutputStream out;
    private ArrayList<ClientHandler> clients;
    private Service service;
    private int userId;
    private Socket socket;
      
    

	public ClientHandler(int userId, Service service, BufferedReader in, DataOutputStream out, ArrayList<ClientHandler> clients, Socket socket) {
        this.userId = userId;
    	this.service = service;
    	this.in = in;
        this.out = out;
        this.clients = clients;
        clients.add(this);
        this.socket = socket;
        start();
    }
    
    public void sendMessage(JSONObject jsonData) {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }

	@Override
	public void run() {
		new Thread(() -> {
	        while (true) {
	            try {
	                String message ;
	            	synchronized (in) {
	            	    message = in.readLine();
	            	}
	                if (message != null) {
		                service.listen(this, message);
		                service.textArea.append("UTF_8 :" + message + "\n");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
    	            try {
    	            	JSONObject jsonActive = new JSONObject();
						jsonActive.put("type", "noActive");
	    	            jsonActive.put("userId", userId);
	    	            service.broadcastActive(userId, jsonActive);
	    	            
	    	            for (ClientHandler client : clients) {
	    	                if (client.getUserId() == userId) {
	    	                	clients.remove(client);
	    	                	break;
	    	                }
	    	            }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	                System.out.println("đóng clientHandler");
	                break;
	            }
	        }
		}).start();			
	}
    
	public Socket getSocket() {
		return socket;
	}

	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	public void setClients(ArrayList<ClientHandler> clients) {
		this.clients = clients;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
