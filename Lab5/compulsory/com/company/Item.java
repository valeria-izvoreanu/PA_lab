package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Item implements Serializable {
    private String id;
    private String name;
    private String location; //file name or Web page

    private Map<String, Object> tags = new HashMap<>();

    public String getName() {
        return name;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    @Override
    public String toString() {
        return name;
    }
}