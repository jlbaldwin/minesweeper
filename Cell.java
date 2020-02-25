package com.jon;

class Cell {

    private char value = '-';           //char displayed when board is printed
    private boolean isBomb = false;
    private boolean isExplored = false;
    private boolean isFlagged = false;
    private char adjacentBombs = '0';   //bomb count in 8 valid directions


    void setAdjacentBombs(char adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

    void setValue(char val) {
        this.value = val;
    }

    void setExplored(boolean explored) {
        isExplored = explored;
    }

    void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    boolean isBomb() {
        return isBomb;
    }

    boolean isExplored() {
        return isExplored;
    }

    boolean isFlagged() {
        return isFlagged;
    }

    char getAdjacentBombs() {
        return adjacentBombs;
    }

    char getValue() {
        return value;
    }
}
