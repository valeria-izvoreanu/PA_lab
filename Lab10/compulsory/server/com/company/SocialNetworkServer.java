package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class SocialNetworkServer {
    // Define the port on which the server is listening
    public static final int PORT = 8100;
    public List<String> people = new ArrayList<>();//registered people
    public Map<String, LinkedList<String>> friendLists = new HashMap<>();
    public Map<String, LinkedList<String>> messages = new HashMap<>();

    public SocialNetworkServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            //control variable to stop server
            Control control = new Control();
            while (!control.isStopped) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                // Execute the client's request in a new thread
                //sending references to control, people, friendLists and messages
                //these being shared variables for the threads
                new ClientNetworkThread(socket, control, people, friendLists, messages).start();
            }
        } catch (IOException e) {
            System.err.println("Something went wrong: " + e);
        } finally {
            System.out.println("Server stopped");
            serverSocket.close();
        }
    }
}
