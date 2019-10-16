package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.chessboard.ChessBoard;

public abstract class ChessPiece {

    private PieceColor pieceColor;
    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;

    public ChessPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
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

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    private String currentPositionAsString() {
        return String.format("Current X: %d\nCurrent Y: %d\nPiece Color: %s", xCoordinate, yCoordinate, pieceColor);
    }

    @Override
    public String toString() {
        return currentPositionAsString();
    }

    public abstract PieceName getName();
    public abstract boolean isValidMoveForPiece(int newXCoordinate, int newYCoordinate);
}