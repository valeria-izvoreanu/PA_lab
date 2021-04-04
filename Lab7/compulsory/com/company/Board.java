package com.company;

import java.util.ArrayList;
import java.util.List;

public class Board extends Thread {
    int n;
    private final Game game;
    private List<Token> usedTokens = new ArrayList<>();

    Board(int n, Game game) {
        this.n = n;
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i < (n * (n - 1)); i++) {
            Token token = new Token(n);
            if (usedTokens.isEmpty()) {
                usedTokens.add(token);
            } else {
                while (checkToken(token)) {
                    token = new Token(n);
                }
                usedTokens.add(token);
            }
            game.setToken(token);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    private boolean checkToken(Token token) {
        for (int i = 0; i < usedTokens.size(); i++) {
            if (token.compareTo(usedTokens.get(i)) == 0) return true;
        }
        return false;
    }
}
