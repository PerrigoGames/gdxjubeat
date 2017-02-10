package com.perrigogames.gdxjubeat.util;

/** Data class to contain information about a particular cell in the game board */
public class BoardCoordinate {

    public int x, y;

    public BoardCoordinate() {
        set(0, 0);
    }

    public BoardCoordinate(int x, int y) {
        set(x, y);
    }

    public BoardCoordinate(BoardCoordinate other) {
        set(other);
    }

    public BoardCoordinate set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public BoardCoordinate set(BoardCoordinate other) {
        return set(other.x, other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BoardCoordinate)) return false;
        BoardCoordinate bc = (BoardCoordinate)other;
        return x == bc.x && y == bc.y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}