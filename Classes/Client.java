package Classes;

import java.io.*;
import java.net.*;

public class Client {
	private int port;
	private String host;
	
	public Client(int portIn, String hostIn){													//Receive IP and Ports
		port = portIn;
		host = hostIn;
	}
	
	 public void connectSocket() {
		 try {
			 Socket s = new Socket(host, port);													//connect to socket
             chat.socketList.add(s);															//add to socketList
             System.out.println("Connected to IP: " + s.getRemoteSocketAddress().toString());
         	}
         catch (IOException e){																	//Socket not connected
             System.out.println("Client creation and connection failed");
         	}
	    }

}


