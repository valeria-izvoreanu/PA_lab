package com.company;

public class Main {

    public static void main(String[] args) {
        //create game as in buffer
        Game game = new Game();
        //create Board as in Producer
        new Board(4, game).start();
        //create Players as in Consumers
        for (int i = 0; i < 4; i++) {
            new Player("player" + i, game).start();
        }
    }
}
