package chat;



public class Chat {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		Thread t = new Server(port);
		t.start();
		System.out.println("Server Thread started");
		Thread m = new Client(port, "localhost");
		m.start();
		System.out.println("Client Thread Started");

	}

}
