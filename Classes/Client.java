package chat;

import java.io.*;
import java.net.*;

public class Client {
	private int port;
	private String host;
	
	public Client(int portIn, String hostIn){
		port = portIn;
		host = hostIn;
		 
	}
	
	 public void connectSocket() {
		 try {
			 Socket s = new Socket(host, port);
             Chat.socketList.add(s);
             System.out.println("Connected to IP: " + s.getRemoteSocketAddress().toString());
         	}
         catch (IOException e){
             System.out.println("Client creation and connection failed");
         	}
	    }

}


