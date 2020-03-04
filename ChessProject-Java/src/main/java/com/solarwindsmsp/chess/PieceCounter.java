package com.solarwindsmsp.chess;

public class PieceCounter {
    private int count = 0;
    private int max;

    public PieceCounter(int max) {
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return max;
    }

    public void inc() {
        count++;
    }
}
