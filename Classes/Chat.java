package chat;

public class Chat {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		Thread t = new Server(port);
		t.start();
		System.out.println("Server Thread started");
		//Enter the IP here
		//Host is given in the arguments as 6606
		Thread m = new Client(6606 , "localhost");
		m.start();
		System.out.println("Client Thread Started");

	}

}

