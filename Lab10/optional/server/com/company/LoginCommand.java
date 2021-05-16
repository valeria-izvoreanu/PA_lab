package com.company;

import java.io.PrintWriter;
import java.util.List;

public class LoginCommand extends Command {
    private PrintWriter out;
    public List<String> people;
    String name = null;

    public LoginCommand(PrintWriter out, List<String> people) {
        this.out = out;
        this.people = people;
    }

    @Override
    public void execute(String name) {
        if (name == null || !people.contains(name)) {
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
