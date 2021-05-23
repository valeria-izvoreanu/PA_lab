package com.company;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;

public class FriendCommand extends Command {
    private PrintWriter out;
    final String uri = "http://localhost:8088/relations";

    public FriendCommand(PrintWriter out) {
        this.out = out;
    }

    public void execute(String name, String friends) {
        if (friends != null) {
            String[] friendList = friends.split(" ");
            RestTemplate restTemplate = new RestTemplate();
            //for each person make post request to create new relationship
            for (String newFriend : friendList) {
                String newURI = uri + "/" + name + "/" + newFriend;
                ResponseEntity<String> response = restTemplate.exchange(
                        newURI, HttpMethod.POST, null,
                        new ParameterizedTypeReference<String>() {
                        });
                out.println(response.getBody());
            }
        }

    }
}
