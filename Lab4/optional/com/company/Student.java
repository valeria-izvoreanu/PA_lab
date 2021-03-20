package com.company;

public class Student {
    private String name;
    private boolean taken=false;
    private int score;

    Student(String name) {
        this.name = name;
    }

    Student(String name, int score) {
        this.name = name;
        this.score=score;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return taken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }
}
