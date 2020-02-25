package com.jon;

public class Cell {

    private char value = '-';
    private boolean isBomb = false;
    private boolean isExplored = false;
    private boolean isFlagged = false;
    private char adjacentBombs = '0';

    public char getValue() {
        return value;
    }

    public void setAdjacentBombs(char adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

    public void setValue(char val) {
        this.value = val;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public char getAdjacentBombs() {
        return adjacentBombs;
    }
}
