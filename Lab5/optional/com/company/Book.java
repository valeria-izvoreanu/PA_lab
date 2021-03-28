package com.company;

import java.io.File;

public class Book extends Item {
    private String description;

    Book(String name, String location) throws InvalidArgumentException {
        super.setId(name);
        super.setName(name);
        File file = new File(location);
        if (!file.exists()) throw new InvalidArgumentException("Invalid path:" + location);
        super.setLocation(location);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
