package com.solarwindsmsp.chess;

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

    public void move(MovementType movementType, int newX, int newY) {
        if (movementType.equals(MovementType.MOVE) && legalMove(newX, newY)) {
            this.setXCoordinate(newX);
            this.setYCoordinate(newY);
        }
    }

    private boolean legalMove(int newX, int newY) {
        return this.getXCoordinate() == newX && checkYByColor(newY);
    }

    private boolean checkYByColor(int newY) {
        if(this.getPieceColor().equals(PieceColor.BLACK))
            return this.getYCoordinate() == newY + 1;
        else
            return this.getYCoordinate() == newY - 1;
    }

    private void refreshBoard(int oldX, int oldY, int newX, int newY) {
//        this.chessBoard.pieces[oldX,oldY] = null;
//        this.chessBoard.pieces[newX,newY] = this;
    }

    private boolean legalCapture(int newX, int newY) {
        //needs to be implemented
        return true;
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
