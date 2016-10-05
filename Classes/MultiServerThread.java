package chat;
import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread{
	 private Socket socket = null;
	 private String message;
	 
	 public MultiServerThread(Socket socket) {
		 super("MultiServerThread");
		 this.socket = socket;
	 }
	 
	 public void run() {
		 
		 while(true){
			 try {
				
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				message = br.readLine();
				if (message != null){
					System.out.println("Message received from client " + socket.getRemoteSocketAddress().toString() + " is: " + message);
				}
				 
				} catch(Exception e){
				System.out.println("Error while reading the message sent.");
			 }
		 }
	 }

}

