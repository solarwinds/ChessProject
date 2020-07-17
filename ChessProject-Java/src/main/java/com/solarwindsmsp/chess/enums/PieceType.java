package com.solarwindsmsp.chess.enums;

public enum PieceType {
    KING(1),
    QUEEN(1),
    KNIGHT(2),
    ROOK(2),
    BISHOP(2),
    PAWN(8);
    private int maxAllowedPieces;

    PieceType(int maxAllowedPieces) {
        this.maxAllowedPieces = maxAllowedPieces;
    }

    public int getMaxAllowedPieces() {
        return maxAllowedPieces;
    }
}
