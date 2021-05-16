package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientNetworkThread extends Thread {
    private final Socket socket;
    public List<String> people;
    public Map<String, LinkedList<String>> friendLists;
    public Map<String, LinkedList<String>> messages;
    Control control;

    public ClientNetworkThread(Socket socket, Control control, List<String> people,
                               Map<String, LinkedList<String>> friendLists, Map<String, LinkedList<String>> messages) {
        this.socket = socket;
        this.control = control;
        this.people = people;
        this.friendLists = friendLists;
        this.messages = messages;
    }

    public void run() {
        try {
            String name = null;
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request = in.readLine();
            Command myCommand;
            while (!request.equals("exit")) {
                if (request.equals("stop")) {
                    //stop server
                    control.isStopped = true;
                    out.println("Server stopped");
                    out.flush();
                    break;
                } else {
                    out.println("Server received request...");
                    String[] command = request.split(" ", 2);//split request to be readable for server
                    if (command[0].equals("register")) {
                        myCommand = new RegisterCommand(out, people);
                        myCommand.execute(command[1]);
                        people = ((RegisterCommand) myCommand).getPeople();

                    } else if (command[0].equals("login")) {
                        myCommand = new LoginCommand(out, people);
                        myCommand.execute(command[1]);
                        name = ((LoginCommand) myCommand).getName();
                        socket.setSoTimeout(200000);
                    } else if (name != null) {
                        //if user logged proceed to do rest of commands
                        switch (command[0]) {
                            case "friend":
                                myCommand = new FriendCommand(out, people, friendLists);
                                myCommand.execute(name, command[1]);
                                friendLists = ((FriendCommand) myCommand).getFriendLists();
                                break;
                            case "send":
                                myCommand = new SendCommand(friendLists, messages);
                                myCommand.execute(name, command[1]);
                                messages = ((SendCommand) myCommand).getMessages();
                                break;
                            case "read":
                                myCommand = new ReadCommand(out, messages);
                                myCommand.execute(name);
                                messages = ((ReadCommand) myCommand).getMessages();
                                break;
                            default:
                                out.println("Command doesn't exist. Try friend, send or read...");
                        }
                    } else {
                        out.println("Please login first...");
                    }
                }
                out.flush();
                request = in.readLine();
            }
            out.close();
            in.close();
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
