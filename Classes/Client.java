package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread{
	private int port;
	private String host;
	InetAddress address;
	
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
             @SuppressWarnings("resource")
            Socket s = new Socket(address, port);
            System.out.println("Connection status: " + s.isConnected());
            System.out.println("Connected to IP: " + s.getRemoteSocketAddress().toString());
            Scanner sc = new Scanner(System.in);
            System.out.println("Write a message to send");
            
            String message = sc.nextLine();
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(message);
            
            System.out.println("Message sent to the server " + s.getRemoteSocketAddress().toString() + ": " + message);
         	}
         catch (IOException e){
             System.out.println("Client creation and connection failed");
         	}
	    }

}

