//This program takes care of creating the server socket that is going to listen to upcoming connections
package chat;

import java.io.*;
import java.net.*;

public class Server extends Thread{
	
	//We will keep track of the variables to create the server
	private int port;
	private boolean listening;

	//The constructor of the class will write our variables
	public Server(int portIn){
		port = portIn;
		listening = true;
	}
	 
	//The server will run on a thread to keep it open while we run our main program.
	//For this, we need a r
	public void run() {
		
		//We will try to create a server socket with the given port
		//After its creation we will keep calling the accept method to keep listening to incoming connections
		//If we get a new connection, we will create a multi server thread and send the socket to the class
		 try (ServerSocket listener = new ServerSocket(port);){  
             while(listening){
            	 new MultiServerThread(listener.accept()).start();
             }
         }
		 //If we cannot create the thread, print an error message
         catch (IOException e) {
        	 System.out.println("Server Socket creation failed.");
         }
     }
}
