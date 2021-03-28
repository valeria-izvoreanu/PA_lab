package com.company;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Item> items = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name, String path) throws InvalidArgumentException {
        this.name = name;
        File file = new File(path);
        if (!file.exists()) throw new InvalidArgumentException("Invalid path:" + path);
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Item findById(String id) {
        return items.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }
}
