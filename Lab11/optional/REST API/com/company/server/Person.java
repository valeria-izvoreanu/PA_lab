package com.company.server;

import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private List<Person> friends = new LinkedList<>();
    private int id;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
