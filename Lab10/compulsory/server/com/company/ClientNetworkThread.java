package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                        if (register(command[1])) {
                            out.println("Successful registration. Your name: " + command[1]);
                        } else {
                            out.println("Name already exists...");
                        }
                    } else if (command[0].equals("login")) {
                        if (login(command[1])) {
                            out.println("Login successful!");
                            name = command[1];
                        } else {
                            out.println("User doesn't exit. Please register first...");
                        }
                    } else if (name != null) {//if user logged proceed to do rest of commands
                        if (command[0].equals("friend")) {
                            List<String> invalidFriends = friend(name, command[1]);
                            if (invalidFriends.size() > 0) {
                                out.println("These people don't exist");
                                for (String badFriend : invalidFriends) {
                                    out.println(badFriend);
                                }
                            } else {
                                out.println("Friends added successfully");
                            }
                        } else if (command[0].equals("send")) {
                            send(name, command[1]);
                        } else if (command[0].equals("read")) {
                            List<String> newMessages = read(name);
                            for (String message : newMessages) {
                                out.println(message);
                            }
                        } else {
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

    private boolean register(String name) {
        if (people.contains(name) || name == null) return false;
        people.add(name);
        return true;
    }

    private boolean login(String name) {
        if (name == null) return false;
        return people.contains(name);
    }

    private List<String> friend(String name, String friends) {
        if (friends == null) {
            return new LinkedList<>();
        }
        String[] friendList = friends.split(" ");
        LinkedList<String> newFriendList = new LinkedList<>();
        List<String> invalidFriends = new ArrayList<>();
        LinkedList<String> dummyFriend = new LinkedList<>();
        dummyFriend.add(name);
        for (String newFriend : friendList) {//add friends to every existent users and vice-versa
            if (people.contains(newFriend)) {
                newFriendList.add(newFriend);
                if (friendLists.containsKey(newFriend)) {
                    friendLists.get(newFriend).add(name);
                } else {
                    friendLists.put(newFriend, dummyFriend);
                }
            } else {
                invalidFriends.add(newFriend);
            }
        }
        if (newFriendList.size() > 0) {
            if (friendLists.containsKey(name)) {
                friendLists.get(name).addAll(newFriendList);
            } else {
                friendLists.put(name, newFriendList);
            }
        }
        return invalidFriends;//return people that haven't been registered
    }

    private void send(String name, String message) {
        LinkedList<String> newMessage = new LinkedList<>();
        if (message == null) {
            message = "";
        }
        newMessage.add(message);
        for (String friend : friendLists.get(name)) {//sends message to every friend
            if (messages.containsKey(friend)) {
                messages.get(friend).add(message);
            } else {
                messages.put(friend, newMessage);
            }
        }
    }

    private List<String> read(String name) {
        List<String> message = messages.get(name).stream().collect(Collectors.toList());
        messages.get(name).subList(0, message.size()).clear();//delete read messages
        return message;
    }
}
