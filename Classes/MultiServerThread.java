package chat;
import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread{
	 private Socket socket = null;
	 private String message;
	 
	 public MultiServerThread(Socket socket) {
		 super("MultiServerThread");
		 this.socket = socket;
		 String remoteString = socket.getRemoteSocketAddress().toString();
		 String[] remoteAddress = remoteString.split(":");
		 int remotePort = Integer.parseInt(remoteAddress[1]);
		 try {
			Socket remote = new Socket(remoteAddress[0], remotePort);
			System.out.println("Client with address " + remoteAddress[0] + ":" + remotePort + " connected to you");
			Chat.socketList.add(remote);
		} catch (UnknownHostException e) {
			System.out.println("Error while accepting connection");
		} catch (IOException e) {
			System.out.println("Error while accepting connection");
		}
		 
	 }
	 
	 public void run() {
		 int reading = 1;
		 while(reading > -1){
			 try {
				reading = socket.getInputStream().read();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				message = br.readLine();
				System.out.println("Message received from client " + socket.getRemoteSocketAddress().toString() + " is: " + message); 
				} catch(Exception e){
				System.out.println("Error while reading the message sent.");
			 }
		 }
	 }

}

