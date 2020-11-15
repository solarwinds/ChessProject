package com.solarwindsmsp.chess;

public abstract class Figure {

    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;
    private ChessBoard chessBoard;

    protected void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    protected ChessBoard getChessBoard() {
        return this.chessBoard;
    }

    protected int getXCoordinate() {
        return xCoordinate;
    }

    protected void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    protected int getYCoordinate() {
        return yCoordinate;
    }

    protected void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    protected PieceColor getPieceColor() {
        return this.pieceColor;
    }

    protected void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    protected abstract void move(MovementType movementType, int newX, int newY) ;

    protected abstract boolean canMove(MovementType movementType, int newX, int newY) ;
}
