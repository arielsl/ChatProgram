package chat;

import java.io.*;
import java.net.*;

public class Client extends Thread{
	private int port;
	private String host;
	
	public Client(int portIn, String hostIn){
		port = portIn;
		host = hostIn;
		 
	}
	
	 public void run() {
		 try {
             Socket s = new Socket(host, port);
             System.out.println("bool: " + s.isConnected());
             System.out.println("external ip: " + s.getRemoteSocketAddress().toString());
             BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
             String answer = input.readLine();
             System.out.println("answer: " + answer);
             s.close();
             System.exit(0);
         }
         catch (IOException e){
             e.printStackTrace();
         }
	    }

}
