//This class runs all accepted connections and keeps them open to continuously read incoming messaged
package chat;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MultiServerThread extends Thread{

    //We will keep track of our variables, the socket that listens and the message received
    private Socket socket = null;
    private String message;

    //Our constructor takes the accepting server socket and stores it
    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

    //As a class that runs on a thread, we need a run method to tell the program what is going to be done
    //on this thread
    public void run() {

        //Make an open while loop to keep reading the messages received in this connection
        while(true){
            try {
                //Use a stream to read the message being received
                //Convert the stream into a string and display the string and the info from where it came from
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                message = br.readLine();
                if (message != null) {
                    System.out.println("Message received from client " + socket.getLocalAddress().toString() + " is: " + message);
                }
                //If an error occure while reading a message, display it to the user and break out of the loop
            } catch(Exception e){
                if(e.getMessage().equals("Connection reset")){
                    System.out.println(socket.getInetAddress().toString() + " has disconnected");
                    List<Integer> index = new ArrayList();
                    int id = 0;
                    //if mutual server disconnects, remove from list
                    for (Socket s : chat.socketList) {
                        if (s.getInetAddress().toString().equals(socket.getInetAddress().toString())){
                            index.add(id);
                        }
                        id++;
                    }
                    for(int i = (index.size() - 1); i > -1; i--) {
                        chat.terminate(index.get(i));
                    }

                }
                else System.out.println("Error while reading the message sent.");
                break;
            }
        }
    }

}


