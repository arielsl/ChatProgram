package chat;
import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread{
	 private Socket socket = null;
	 
	 public MultiServerThread(Socket socket) {
		 super("MultiServerThread");
		 this.socket = socket;
	 }
	 
	 public void run() {
		 System.out.println("Trying to read the message");
		 try {
			 InputStream is = socket.getInputStream();
			 InputStreamReader isr = new InputStreamReader(is);
			 BufferedReader br = new BufferedReader(isr);
			 String number = br.readLine();
			 System.out.println("Message received from client is "+number);
		 } catch(Exception e){
			 System.out.println("Message reader failed");
		 }
	 }

}
