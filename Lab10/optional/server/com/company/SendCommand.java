package com.company;

import java.util.LinkedList;
import java.util.Map;

public class SendCommand extends Command {
    public Map<String, LinkedList<String>> messages;
    public Map<String, LinkedList<String>> friendLists;

    public SendCommand(Map<String, LinkedList<String>> friendLists, Map<String, LinkedList<String>> messages) {
        this.friendLists = friendLists;
        this.messages = messages;
    }

    public void execute(String name, String message) {
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

    public Map<String, LinkedList<String>> getMessages() {
        return messages;
    }
}
