package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Thread {
    private final String name;
    private List<Token> playerSequence = new ArrayList<>();
    private List<Token> playerTokens = new ArrayList<>();
    private final Game game;

    public Player(String name, Game game) {
        this.game = game;
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            playerTokens.add(game.getToken());
        }
        System.out.print(name + ": ");
        System.out.println(Arrays.asList(playerTokens));
    }

    public List<Token> getPlayerTokens() {
        return playerTokens;
    }
}
