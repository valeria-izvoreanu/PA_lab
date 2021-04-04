package com.company;


public class Game {
    private Token token = new Token();
    private boolean available = false;

    public synchronized Token getToken() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notify();
        return token;
    }

    public synchronized void setToken(Token token) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.token = token;
        available = true;
        notify();
    }
}
