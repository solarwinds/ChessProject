package com.solarwindsmsp.chess;

public class Pawn extends Piece implements PieceActions {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {

        if (!getChessBoard().isLegalBoardPosition(newX, newY)) {
            invalidatePiece();
            return;
        }

        switch (movementType) {
            case MOVE:
                if (!isLegalMove(newX, newY)) {
                    return;
                }

                getChessBoard().getPieces()[getXCoordinate()][getYCoordinate()] = null;
                setXCoordinate(newX);
                setYCoordinate(newY);
                getChessBoard().getPieces()[newX][newY] = this;
                break;
            case CAPTURE:
                if (!isLegalCapture(newX, newY)) {
                    return;
                }
                getChessBoard().getPieces()[getXCoordinate()][getYCoordinate()] = null;
                setXCoordinate(newX);
                setYCoordinate(newY);
                getChessBoard().getPieces()[newX][newY] = this;
                break;
        }
    }

    public boolean isLegalMove(int newX, int newY){
        switch (getPieceColor()) {
            case WHITE:
                return newX == getXCoordinate() && (newY - getYCoordinate()) == 1;
            case BLACK:
                return newX == getXCoordinate() && (getYCoordinate() - newY) == 1;
            default:
                return false;
        }
    }

    public boolean isLegalCapture(int newX, int newY) {
        boolean captureExists = getChessBoard().getPiece(newX, newY) != null
                && getChessBoard().getPiece(newX, newY).getPieceColor() != getPieceColor();

        switch (getPieceColor()) {
            case WHITE:
                return captureExists
                        && newY - getYCoordinate() == 1
                        && (newX == getXCoordinate() - 1 || newX == getXCoordinate() + 1);
            case BLACK:
                return captureExists
                        && getYCoordinate() - newY  == 1
                        && (newX == getXCoordinate() - 1 || newX == getXCoordinate() + 1);
            default:
                return false;
        }
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
