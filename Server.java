package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread{
	
	private int port;

	public Server(int portIn){
		port = portIn;
	}
	 
	public void run() {
		 try {
             ServerSocket listener = new ServerSocket(port);
             System.out.println("Created server");
             System.out.println(listener.getLocalSocketAddress());
             try {
            	 System.out.println("Entering while loop");
                 while (true) {
                     Socket socket = listener.accept();
                     try {
                         Scanner s = new Scanner(System.in);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         out.println(s.nextLine());
                         s.close();
                     } finally {
                         socket.close();
                     }
                 }
             } finally {
                 listener.close();
             }
         }
         catch (IOException e) {
             e.printStackTrace();
         }
     }
}
