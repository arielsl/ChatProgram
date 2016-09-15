package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServerClient extends Thread {
   private ServerSocket serverSocket;
   
   //Use this class method to create a socket with the given port
   //Add a timeout in case of standby
   public ServerClient(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(30000);
   }
   
   public static void main(String [] args) {
	  //Get the port number from the arguments and parse it as an integer
      int port = Integer.parseInt(args[0]);
      //Setup variables for the loop to create the server socket
      int createServer = 0;
      int newPort = 0;
      int portMethod = 0;
      //Open a scanner to read input
      Scanner sc = new Scanner(System.in);
      
      //If there is no socket created, use 0 and enter the loop to create one
      while (createServer == 0){
	      
    	  //Try and catch to create a socket
    	  try {
    		 ///If it is the initial creation, use the console arguments given
    		 if (portMethod == 0){
		         Thread t = new ServerClient(port);
		         t.start();
		         createServer = 1;
    		 }else{
    			 //If it is not the first try, use the integer given as input
    			 Thread t = new ServerClient(newPort);
		         t.start();
		         createServer = 1;
    		 }
	      }catch(IOException e) {
	    	 //If there is an error creating the socket, ask for a new port number
	    	 //Then make sure to jump to the correct loop position
	         System.out.println("Socket creation failed");
	         System.out.println("Type a new port number: ");
	         newPort = sc.nextInt();
	         createServer = 0;
	         portMethod = 1;
	      }
    	  
      }
      
      //Create a loop to give option to the user
      int choice = 9;
      while (choice != 8){
    	  //Print the choices to the user
    	  System.out.println("Select a number with the preferred command:");
    	  System.out.println("1.- Help");
    	  System.out.println("2.- myIP");
    	  System.out.println("3.- myPort");
    	  System.out.println("4.- Connect to a server");
    	  System.out.println("5.- List");
    	  System.out.println("6.- Terminate");
    	  System.out.println("7.- Send message");
    	  System.out.println("8.- Exit");
    	  choice = sc.nextInt();
    	  
    	  //Make a switch statement to handle the option selected
    	  switch(choice){
    	  case 1:
    		  break;
    	  case 2:
    		  break;
    	  case 3:
    		  break;
    	  case 4:
    		  break;
    	  case 5:
    		  break;
    	  case 6:
    		  break;
    	  case 7:
    		  break;
    	  case 8:
    		  break;
    	  default:
    		  break;
    	  }
    	  
      }
      
      //Close the input scanner
      sc.close();
   }
}
