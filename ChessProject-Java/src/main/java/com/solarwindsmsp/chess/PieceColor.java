package com.solarwindsmsp.chess;

public enum PieceColor {
    BLACK,
    WHITE;

    public PieceColor opposite() {
        if (this.equals(PieceColor.BLACK)) {
            return PieceColor.WHITE;
        }

        return PieceColor.BLACK;
    }
}
