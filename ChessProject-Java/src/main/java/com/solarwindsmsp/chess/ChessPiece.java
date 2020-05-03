package com.solarwindsmsp.chess;

public class ChessPiece {

    protected ChessBoard chessBoard;
    protected int xCoordinate;
    protected int yCoordinate;
    protected PieceColor pieceColor;

    public ChessPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void invalidatePiece() {
        this.xCoordinate = -1;
        this.yCoordinate = -1;
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

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
}
