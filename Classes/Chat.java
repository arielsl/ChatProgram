package Classes;

import java.io.*;
import java.net.*;
import java.util.*;

public class Chat {
	
	public static String ip;
	public static int port;
	public static List<Socket> socketList = new ArrayList<Socket>();
	private static Scanner sc;

	public static void main(String[] args) {
		boolean portFound = false;
		System.out.println("Enter port:");
		sc = new Scanner(System.in);
		while(!portFound){
			if(sc.hasNextInt()){
				port = sc.nextInt();
				if(port >= 1024 && port < 65535)
					portFound = true;
			}
			sc.nextLine();
			if(!portFound)
				System.out.println("Please enter a valid port number");
		}
		Thread t = new Server(port);
		t.start();
		try {
			getIp();
		} catch (Exception e) {
			System.out.println("Could not retreat IP");
		}
		getPort();
		System.out.println("Server created at address: " + ip + ":" + port);
		
		int choice = 10;
		sc = new Scanner(System.in);
		while(choice != 8){
			System.out.println("Select a command with the given number: ");
			System.out.println("1. Help");
			System.out.println("2. My ip");
			System.out.println("3. My port");
			System.out.println("4. Connect");
			System.out.println("5. List");
			System.out.println("6. Terminate");
			System.out.println("7. Send");
			System.out.println("8. Exit");

			while(!sc.hasNextInt()){
				sc.nextLine();
				System.out.println("Please enter a valid number");
			}
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice){
				case 1: getHelp();
					break;
				case 2: try {
						getIp();
					} catch (Exception e) {
						System.out.println("Could not retreat IP");
					}
					break;
				case 3: getPort();
					break;
				case 4: connect();
					break;
				case 5: getList();
					break;
				case 6: terminate();
					break;
				case 7: send();
					break;
				case 8: endProgram();
					break;
				default: System.out.println("Not a valid command.");
					break;	
				}
		}
		sc.close();

    }
	
	public static void getHelp(){
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

    public static void getIp() throws Exception {
        URL findIP = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    findIP.openStream()));
            ip = in.readLine();
            System.out.println("My IP is: " + ip);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void getPort(){
    	System.out.println("My port is: " + port);
    }
    
    public static void connect(){
    	
    	System.out.println("Enter the IP of the server:");
    	String serverIp = sc.next();
    	sc.nextLine();
    	System.out.println("Enter the port of the server:");
    	int serverPort = sc.nextInt();
    	sc.nextLine();
    	Client m = new Client(serverPort , serverIp);
		m.connectSocket();
		
    }
    
    public static void getList(){
		if(socketList.size() > 0) {
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
    	if(socketList.size() > 0) {
			System.out.println("Select the index of the socket you wish to terminate:");
			sc.nextLine();
			if(!sc.hasNextInt()){
				int index = sc.nextInt();
				sc.nextLine();
				if(index > -1 && index < socketList.size()) {
					Socket s = socketList.get(index);
					try {
						s.close();
						socketList.remove(index);
					} catch (IOException e) {
						System.out.println("Could not terminate connection.");
					}
				}
				else System.out.println("Please insert valid index");
			}
			else System.out.println("Please insert valid index");
		}
		else System.out.println("There is no sockets to terminate");
    }
    
    public static void send(){
    	if(socketList.size() > 0) {
			System.out.println("Select the index of the socket you wish to send a message to:");
//			sc.nextLine();
			if(sc.hasNextInt()) {
				int index = sc.nextInt();
				sc.nextLine();
				if(index > -1 && index < socketList.size()) {
					Socket s = socketList.get(index);
					//Send the message to the server
					try {
						OutputStream os = s.getOutputStream();
						OutputStreamWriter osw = new OutputStreamWriter(os);
						@SuppressWarnings("unused")
						BufferedWriter bw = new BufferedWriter(osw);

						System.out.println("Write a message to send:");
						//sc = new Scanner(System.in);
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
    
    public static void endProgram(){
    	for(Socket s: socketList){
    		try {
				s.close();
			} catch (IOException e) {
				System.out.println("Failed to close connection");
			}
    	}
    	System.exit(-1);
    	
    }
}
