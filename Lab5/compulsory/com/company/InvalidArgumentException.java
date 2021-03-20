package com.company;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String path) {
        super(path);
    }
}
