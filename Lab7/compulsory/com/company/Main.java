package com.company;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        new Board(4, game).start();
        for (int i = 0; i < 4; i++) {
            new Player("player" + i, game).start();
        }
    }
}
