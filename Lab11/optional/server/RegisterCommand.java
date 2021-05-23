package com.company;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;

public class RegisterCommand extends Command {
    private PrintWriter out;
    final String uri = "http://localhost:8088/persons";

    public RegisterCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void execute(String name) {
        RestTemplate restTemplate = new RestTemplate();
        //send post request to API
        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.POST, new HttpEntity<>(name),
                new ParameterizedTypeReference<String>() {
                });
        out.println(response.getBody());
    }
}
