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

	    System.out.println("Client Thread Started");
		
        try {
            System.out.println("My ip: " + getIp());
        }
        catch (Exception e){

        }

    }

    public static String getIp() throws Exception {
        URL findIP = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    findIP.openStream()));
            String ip = in.readLine();
            return ip;
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
}

