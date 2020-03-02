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

    public void Move(MovementType movementType, int newX, int newY) {
        if (movementType == MovementType.CAPTURE) {
            throw new UnsupportedOperationException("Need to implement Pawn.Move() for CAPTURE") ;
        }

        if (!chessBoard.IsLegalBoardPosition(newX, newY)) {
            return;
        }

        if (newX == xCoordinate && newY == yCoordinate - 1) {
            xCoordinate = newX;
            yCoordinate = newY;
        }
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        return String.format("%d,%d:%s pawn", xCoordinate, yCoordinate, pieceColor);
    }
}
