package com.company;

import java.io.PrintWriter;
import java.util.List;

public class RegisterCommand extends Command {
    private PrintWriter out;
    public List<String> people;

    public RegisterCommand(PrintWriter out, List<String> people) {
        this.out = out;
        this.people = people;
    }

    @Override
    public void execute(String name) {
        if (people.contains(name) || name == null) {
            out.println("Name already exists...");
        } else {
            out.println("Successful registration. Your name: " + name);
            people.add(name);
        }
    }

    public List<String> getPeople() {
        return people;
    }
}
