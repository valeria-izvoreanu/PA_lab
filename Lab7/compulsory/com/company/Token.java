package com.company;

import java.util.Random;

public class Token implements Comparable<Token> {
    private int first;
    private int second;

    Token() {
    }

    Token(int n) {
        //choose random token
        Random random = new Random();
        this.first = random.nextInt(n) + 1;
        int second = random.nextInt(n) + 1;
        while (second == this.first) {
            second = random.nextInt(n) + 1;
        }
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public int compareTo(Token other) {
        //compare Tokens according to it's numbers
        if (other == null) throw new NullPointerException();
        if ((this.second == other.getSecond()) && (this.first == other.getFirst())) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return "(" + first +
                ", " + second +
                ')';
    }

}
