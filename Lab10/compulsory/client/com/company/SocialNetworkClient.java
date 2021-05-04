package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocialNetworkClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

            String command = "";
            while (!command.equals("exit")) {
                command = read.readLine();//read commands from keyboard
                out.println(command);
                String line = in.readLine();//read responses from server
                //print every line
                while (line != null) {
                    System.out.println(line);
                    // read next line
                    if (in.ready()) {
                        line = in.readLine();
                    } else break;
                }
            }
            read.close();
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
