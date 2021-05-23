package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand extends Command {
    private PrintWriter out;
    final String uri = "http://localhost:8088/persons";
    String name = null;

    public LoginCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void execute(String name) {
        RestTemplate restTemplate = new RestTemplate();
        //get request to get all people
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        List<Person> people = new ArrayList<>();
        try {
            people = mapper.readValue(response.getBody(), new TypeReference<List<Person>>() {
            });
        } catch (Exception e) {
            out.println("Error");
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            names.add(people.get(i).getName());
        }
        //check if person exists
        if (name == null || !names.contains(name)) {
            out.println("User doesn't exit. Please register first...");
        } else {
            out.println("Login successful!");
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }
}
