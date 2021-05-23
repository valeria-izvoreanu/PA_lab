package com.company;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadCommand extends Command {
    private PrintWriter out;
    public Map<String, LinkedList<String>> messages;

    public ReadCommand(PrintWriter out, Map<String, LinkedList<String>> messages) {
        this.out = out;
        this.messages = messages;
    }

    public void execute(String name) {
        //get copy of messages
        List<String> message = messages.get(name).stream().collect(Collectors.toList());
        messages.get(name).subList(0, message.size()).clear();//delete read messages
        for (String readMessage : message) {
            out.println(readMessage);
        }
    }

    public Map<String, LinkedList<String>> getMessages() {
        return messages;
    }
}
