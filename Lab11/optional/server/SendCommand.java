package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SendCommand extends Command {
    public Map<String, LinkedList<String>> messages;
    String uri = "http://localhost:8088/relations";

    public SendCommand(Map<String, LinkedList<String>> messages) {
        this.messages = messages;
    }

    public void execute(String name, String message) {
        LinkedList<String> newMessage = new LinkedList<>();
        if (message == null) {
            message = "";
        }
        newMessage.add(message);
        List<String> friendLists = getFriends(name);
        for (String friend : friendLists) {//sends message to every friend
            if (messages.containsKey(friend)) {
                messages.get(friend).add(message);
            } else {
                messages.put(friend, newMessage);
            }
        }
    }

    private List<String> getFriends(String name) {
        RestTemplate restTemplate = new RestTemplate();
        uri = uri + "/" + name;
        //get request to get all friends from database
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        List<Person> friends = new ArrayList<>();
        try {
            friends = mapper.readValue(response.getBody(), new TypeReference<List<Person>>() {
            });
        } catch (Exception e) {
            System.out.println("Error");
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            names.add(friends.get(i).getName());
        }
        return names;
    }

    public Map<String, LinkedList<String>> getMessages() {
        return messages;
    }
}
