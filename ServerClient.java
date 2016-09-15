package chat;

import java.net.*;
import java.io.*;

public class ServerClient extends Thread {
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(30000);
   }
   
   public static void main(String [] args) {
      int port = Integer.parseInt(args[0]);
      try {
         Thread t = new Server(port);
         t.start();
      }catch(IOException e) {
         System.out.println("Socket creation failed");
      }
   }
}
