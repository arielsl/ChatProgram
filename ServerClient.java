package com.company;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Maurice on 9/15/2016.
 */

public class SocketTest {

    private List<Socket> list;

    public static void main(String[] args) throws IOException {

        startServer();
        startSender();
    }

    public static void startSender() throws IOException{
//        String host = "130.182.24.40";
        String host = "localhost";
        new Thread() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket(host, 9090);
                    System.out.println("bool: " + s.isConnected());
                    System.out.println("external ip: " + s.getRemoteSocketAddress().toString());
                    BufferedReader input =
                            new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String answer = input.readLine();
                    System.out.println("answer: " + answer);
//                    JOptionPane.showMessageDialog(null, answer);
                    System.exit(0);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void startServer() {
        (new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket listener = new ServerSocket(9090);
                    try {
                        while (true) {
                            Socket socket = listener.accept();
                            try {
                                Scanner s = new Scanner(System.in);
                                PrintWriter out =
                                        new PrintWriter(socket.getOutputStream(), true);
                                out.println(s.nextLine());
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
        }).start();
    }
}
