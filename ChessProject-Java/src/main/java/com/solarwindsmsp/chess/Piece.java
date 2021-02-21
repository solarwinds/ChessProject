package com.solarwindsmsp.chess;

public class Piece {
    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private PieceColor pieceColor;
    private ChessBoard chessBoard;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean addToBoard() {
        if(chessBoard == null || xCoordinate == -1 || yCoordinate == -1) {
            return false;
        }
        return chessBoard.addPiece(this);
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
