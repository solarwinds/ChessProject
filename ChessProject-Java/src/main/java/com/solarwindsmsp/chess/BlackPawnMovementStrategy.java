package com.solarwindsmsp.chess;

public class BlackPawnMovementStrategy implements MovementStrategy{
    @Override
    public boolean canMove(Piece piece, int x, int y) {
        if (!piece.getChessBoard().isLegalBoardPosition(x, y)){
            return false;
        }

        if (piece.getXCoordinate() != x) {
            return false; // can only move forward
        }

        if (piece.getYCoordinate() - y != 1) { // can only move forward by 1
            return false;
        }

        return true;

    }
}
