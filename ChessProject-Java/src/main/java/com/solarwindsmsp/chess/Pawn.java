package com.solarwindsmsp.chess;

public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public void move(MovementType movementType, int newX, int newY) {
        if (movementType == MovementType.CAPTURE) {
            throw new UnsupportedOperationException("Need to implement Pawn.Move() for CAPTURE");
        }

        if (!chessBoard.isLegalBoardPosition(newX, newY) || chessBoard.getPieces()[newX][newY] != null) {
            return;
        }

        int directionOfTravel = pieceColor == PieceColor.BLACK ? -1 : +1;

        if (newX == xCoordinate && newY == yCoordinate + directionOfTravel) {
            chessBoard.getPieces()[newX][newY] = this;
            chessBoard.getPieces()[xCoordinate][yCoordinate] = null;
            xCoordinate = newX;
            yCoordinate = newY;
        }
    }

    @Override
    public String toString() {
        return pieceColor == PieceColor.BLACK ? "p" : "P";
    }

    protected String CurrentPositionAsString() {
        return String.format("%d,%d:%s pawn", xCoordinate, yCoordinate, pieceColor);
    }
}
