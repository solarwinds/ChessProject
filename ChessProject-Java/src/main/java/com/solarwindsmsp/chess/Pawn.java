package com.solarwindsmsp.chess;

public class Pawn implements Piece {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    @Override
    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    @Override
    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    @Override
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
        if(chessBoard.isLegalBoardPosition(newX,newY)) {
            if(MovementType.MOVE.equals(movementType) && isLegalMove(newX,newY) ) {
                chessBoard.move(this.xCoordinate, this.yCoordinate, newX, newY);
                this.xCoordinate = newX;
                this.yCoordinate = newY;
            }
        }
    }

    private boolean isLegalMove(int newX, int newY) {
        int direction = PieceColor.WHITE.equals(this.pieceColor)?1:-1;
        return newX-this.xCoordinate==direction && this.yCoordinate==newY;
    }

    @Override
    public String toString() {
        String eol = System.lineSeparator();
        return String.format("Current X: %2$d%1$sCurrent Y: %3$d%1$sPiece Color: %4$s",
                eol, xCoordinate, yCoordinate, pieceColor.name());
    }
}
