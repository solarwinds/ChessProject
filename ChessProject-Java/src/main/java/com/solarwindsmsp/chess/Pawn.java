package com.solarwindsmsp.chess;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
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

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void move(MovementType movementType, int newX, int newY) throws IllegalBoardCoordinatesException, InvalidArgumentException {
        if (movementType != MovementType.MOVE) {
            throw new InvalidArgumentException(new String[]{movementType.name()});
        }

        IllegalBoardCoordinatesException e =
                new IllegalBoardCoordinatesException(
                        "Invalid new coordinates for " + pieceColor.name() + " piece with current coordinates x=" + xCoordinate + " and y=" + yCoordinate + ".",
                        newX, newY);
        switch (pieceColor) {
            case BLACK:
                // A black piece can only move downwards
                if (yCoordinate - newY != 1 || xCoordinate != newX) {
                    throw e;
                }
                break;
            case WHITE:
                // A white piece can only move upwards
                if (newY - yCoordinate != 1 || xCoordinate != newX) {
                    throw e;
                }
                break;
        }

        chessBoard.removePiece(xCoordinate, yCoordinate);
        chessBoard.addPiece(this, newX, newY);
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
