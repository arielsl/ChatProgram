package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class chat {
	
	public static String ip;
	public static int port;
	public static List<Socket> socketList = new ArrayList<Socket>();
	private static Scanner sc;

	public static void main(String[] args) {

		boolean portFound = false;
		System.out.println("Enter port:");
		sc = new Scanner(System.in);

		while(!portFound){														//Find Valid Ports
			if(sc.hasNextInt()){
				port = sc.nextInt();
				if(port >= 1024 && port < 65535)								//Valid Ports
					portFound = true;
			}
			sc.nextLine();
			if(!portFound)
				System.out.println("Please enter a valid port number");
		}

		Thread t = new Server(port);											//Send Port int as Server ports
		t.start();																//Start Server

		getIp();																//Show External IP
		getPort();																//Show Port
		
		int choice = 10;
		sc = new Scanner(System.in);

		while(choice != 8){														//Chat Choices
			System.out.println("\nSelect a command with the given number: ");
			System.out.println("1. Help");
			System.out.println("2. My ip");
			System.out.println("3. My port");
			System.out.println("4. Connect");
			System.out.println("5. List");
			System.out.println("6. Terminate");
			System.out.println("7. Send");
			System.out.println("8. Exit");

			while(!sc.hasNextInt()){											//Loop until valid int input
				sc.nextLine();
				System.out.println("Please enter a valid number");
			}
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice){														//Get Choice
				case 1: getHelp();												//Help
					break;
				case 2: try {													//Get IP
						getIp();
					} catch (Exception e) {
						System.out.println("Could not retreat IP");
					}
					break;
				case 3: getPort();												//Get Port
					break;
				case 4: connect();												//Connect to server
					break;
				case 5: getList();												//Display List
					break;
				case 6: terminate();											//Terminate a connection
					break;
				case 7: send();													//Send message to connected Server
					break;
				case 8: System.exit(-1);										//Exit
					break;
				default: System.out.println("Not a valid command.");			//Wrong Input
					break;	
				}
		}
		sc.close();

    }
	
	public static void getHelp(){												//Help text
		System.out.println("Short description of all methods available: ");
		System.out.println("Help: Display information about the available user interface options or command manual.");
		System.out.println("My ip: Display the IP address of this process.");
		System.out.println("My port: Display the port on which this process is listening for incoming connections.");
		System.out.println("Connect: This command establishes a new TCP connection to the specified <destination> at the specified < port no>. ");
		System.out.println("List: Display a numbered list of all the connections this process is part of");
		System.out.println("Terminate: This command will terminate the connection listed under the specified number");
		System.out.println("Send: This will send the message to the host on the connection that is designated");
		System.out.println("Exit: Close all connections and terminate this process");
	}

    public static void getIp(){													//Get IP
    	try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            ip = ipAddr.getHostAddress();
            System.out.println("My IP is: " + ip);
        } catch (UnknownHostException ex) {
            System.out.println("IP extraction failed");
        }
    }
    
    public static void getPort(){
    	System.out.println("My port is: " + port);
    } //Get Port
    
    public static void connect(){												//Connect to Server
    	
    	System.out.println("Enter the IP of the server:");
    	String serverIp = sc.next();
    	sc.nextLine();
    	System.out.println("Enter the port of the server:");
		if(sc.hasNextInt()) {													//data validation
			int serverPort = sc.nextInt();
			sc.nextLine();
			Client m = new Client(serverPort, serverIp);                        //Send IP and Port to Client as variables
			m.connectSocket();													//connect to provided IP and Ports
		}
		else {
			sc.nextLine();
			System.out.print("Not a valid Port");
		}
    }
    
    public static void getList(){												//Display List
		if(socketList.size() > 0) {												//Only Display if connections are available
			int id = 0;
			System.out.println("ID	Address");
			for (Socket s : socketList) {
				System.out.println(id + "	" + s.getRemoteSocketAddress());
				id++;
			}
		}
		else {
			System.out.println("No Connections");
		}
    }
    
    public static void terminate(){
    	if(socketList.size() > 0) {												//Only allow to terminate when there are
			System.out.println("Select the index of the socket you wish to terminate:");  //connections
			if(sc.hasNextInt()){												//Input validation
				int index = sc.nextInt();
				if(index > -1 && index < socketList.size()) {					//Input validation
					Socket s = socketList.get(index);
					try {
						s.close();												//Close and remove socket
						socketList.remove(index);
						sc.nextLine();
					} catch (IOException e) {
						System.out.println("Could not terminate connection.");
					}
				}
				else {
					System.out.println("Please insert valid index");
					sc.nextLine();
				}
			}else{
				System.out.println("Please insert valid index");
				sc.nextLine();
			}
		}else{
			System.out.println("There is no sockets to terminate");
			sc.nextLine();
		}
		
    }
    
    public static void send(){
    	if(socketList.size() > 0) {												//Only allow send messages when there are
			System.out.println("Select the index of the socket you wish to send a message to:");	//valid connections
			if(sc.hasNextInt()) {												//Input validation
				int index = sc.nextInt();
				sc.nextLine();
				if(index > -1 && index < socketList.size()) {					//Input Validation
					Socket s = socketList.get(index);
					try {														//Send the message to the server
						System.out.println("Write a message to send:");
						String message = sc.nextLine();
						PrintWriter out = new PrintWriter(s.getOutputStream(), true);
						out.println(message);
						System.out.println("Message sent to the server " + s.getRemoteSocketAddress() + ": " + message);

					} catch (IOException e) {
						System.out.println("Message failed to sent.");
					}
				}
				else System.out.println("Please insert valid index");
			}
			else System.out.println("Please insert valid index");
		}
		else System.out.println("There are no connections");
    }
    
}
