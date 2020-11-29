package com.solarwindsmsp.chess.models;

import com.solarwindsmsp.chess.PieceColor;
import com.solarwindsmsp.chess.actions.MoveAction;

public abstract class Piece implements MoveAction {

    public enum Type {
        Pawn
    }

    private int xCoordinate;
    private int yCoordinate;
    private final PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public void updatePosition(int newX, int newY) {
        setXCoordinate(newX);
        setYCoordinate(newY);
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }

}
