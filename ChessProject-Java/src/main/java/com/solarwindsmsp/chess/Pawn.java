package com.solarwindsmsp.chess;

public class Pawn extends Figure {

    public Pawn(PieceColor pieceColor) {
        this.setXCoordinate(-1);
        this.setYCoordinate(-1);
        this.setPieceColor(pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (!getChessBoard().isLegalBoardPosition(newX, newY) || !canMove(movementType, newX, newY)) {
            return;
        }
        if (MovementType.MOVE.equals(movementType)) {
            this.setXCoordinate(newX);
            this.setYCoordinate(newY);
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    public boolean canMove(MovementType movementType, int newX, int newY) {
        return PieceColor.WHITE.equals(getPieceColor()) ? newY == getYCoordinate() + 1 : newY == getYCoordinate() - 1;
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, getXCoordinate(), getYCoordinate(), getPieceColor());
    }
}
