package com.solarwindsmsp.chess;

public class Pawn implements Piece{

    private ChessBoard chessBoard;
    private int xCoordinate = -1;
    private int yCoordinate = -1;
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

    @Override
    public PieceColor getColor() {
        return this.pieceColor;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void move(MovementType movementType, int newX, int newY) {
        this.chessBoard.movePiece(this, newX, newY);
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
