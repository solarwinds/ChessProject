package com.solarwindsmsp.chess;

import lombok.Data;

@Data
public abstract class Piece {
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public enum TypePiece{
        PAWN, BISHOP, KING //etc
    }

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public abstract void move(MovementType movementType, int newX, int newY);

    @Override
    public String toString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}

