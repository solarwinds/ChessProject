package com.solarwindsmsp.chess;

public class Piece {

    private ChessBoard chessBoard;

    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Piece(ChessBoard chessBoard, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        this.chessBoard = chessBoard;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }
}
