package chat;

import java.io.*;
import java.net.*;

public class Client extends Thread{
	private int port;
	private String host;
	private InetAddress address;
	
	public Client(int portIn, String hostIn){
		port = portIn;
		host = hostIn;
		try {
			address = InetAddress.getByName(host);
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		 
	}
	
	 public void run() {
		 System.out.println("Trying to create and connect a new socket to a server.");
		 try {
			 Socket s = new Socket(address, port);
             Chat.socketList.add(s);
             System.out.println("Connection status: " + s.isConnected());
             System.out.println("Connected to IP: " + s.getRemoteSocketAddress().toString());
         	}
         catch (IOException e){
             System.out.println("Client creation and connection failed");
         	}
	    }

}


