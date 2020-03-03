package com.solarwindsmsp.chess;

public class King extends Piece {

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (movementType == MovementType.CAPTURE) {
            throw new UnsupportedOperationException("Need to implement King.Move() for CAPTURE");
        }

        if (!chessBoard.isLegalBoardPosition(newX, newY)) {
            return;
        }

        int directionOfTravel = -1; // can only move like a BLACK PAWN for the moment

        if (newX == xCoordinate && newY == yCoordinate + directionOfTravel) {
            chessBoard.getPieces()[newX][newY] = this;
            chessBoard.getPieces()[xCoordinate][yCoordinate] = null;
            xCoordinate = newX;
            yCoordinate = newY;
        }
    }

}
