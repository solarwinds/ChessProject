package com.solarwindsmsp.chess;

public enum PieceColor {

    BLACK(0), WHITE(1);

    private final int code;

    private PieceColor(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
