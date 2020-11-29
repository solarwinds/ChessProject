package com.solarwindsmsp.chess.models;

import com.solarwindsmsp.chess.PieceColor;

class Pawn extends Piece {

    private static final int ONE_SPACE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isLegalMovePosition(int newX, int newY) {
        switch (getPieceColor()) {
            case BLACK:
                return newX == getXCoordinate() && newY == getYCoordinate() - ONE_SPACE;
            case WHITE:
                return newX == getXCoordinate() && newY == getYCoordinate() + ONE_SPACE;
        }

        return false;
    }

    @Override
    public boolean isLegalCapturePosition(int newX, int newY) {
        switch (getPieceColor()) {
            case BLACK:
                boolean blackDiagonal = (newX == getXCoordinate() + ONE_SPACE || newX == getXCoordinate() - ONE_SPACE)
                        && newY == getYCoordinate() - ONE_SPACE;
                return isLegalMovePosition(newX, newY) || blackDiagonal;
            case WHITE:
                boolean whiteDiagonal = (newX == getXCoordinate() + ONE_SPACE || newX == getXCoordinate() - ONE_SPACE)
                        && newY == getYCoordinate() + ONE_SPACE;
                return isLegalMovePosition(newX, newY) || whiteDiagonal;
        }

        return false;
    }

}
