package chat;

import java.io.*;
import java.net.*;

public class Server extends Thread{
	
	private int port;
	private boolean listening;

	public Server(int portIn){
		port = portIn;
		listening = true;
	}
	 
	public void run() {
		System.out.println("Creation of a new Server socket and a new socket thread.");
		 try (ServerSocket listener = new ServerSocket(port);){  
             while(listening){
            	 new MultiServerThread(listener.accept()).start();
             }
         }
         catch (IOException e) {
        	 System.out.println("Server Socket crreation failed.");
         }
     }
}

