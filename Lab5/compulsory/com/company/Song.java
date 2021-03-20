package com.company;

import java.io.File;

public class Song extends Item {
    private String singer;

    Song(String name, String location) throws InvalidArgumentException {
        super.setId(name);
        super.setName(name);
        File file = new File(location);
        if (!file.exists()) throw new InvalidArgumentException("Invalid path:" + location);
        super.setLocation(location);
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
