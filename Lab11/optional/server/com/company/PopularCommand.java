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

public class PopularCommand {
    private PrintWriter out;
    String uri = "http://localhost:8088/relations";

    public PopularCommand(PrintWriter out) {
        this.out = out;
    }


    public void getPopular() {
        execute("/popular");
    }

    public void getUnpopular() {
        execute("/unpopular");
    }

    private void execute(String type) {
        uri = uri + type;
        RestTemplate restTemplate = new RestTemplate();
        //get request to the API in JSON form
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });
        ObjectMapper mapper = new ObjectMapper();
        List<Person> people = new ArrayList<>();
        //turn JSON to list of Person
        try {
            people = mapper.readValue(response.getBody(), new TypeReference<List<Person>>() {
            });
        } catch (Exception e) {
            out.println("Error");
        }
        for (int i = 0; i < people.size(); i++) {
            out.println(people.get(i).getName());
        }
    }
}
