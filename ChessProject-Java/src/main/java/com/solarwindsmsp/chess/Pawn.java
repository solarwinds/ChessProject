package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exceptions.UnknownColorException;

public class Pawn extends ChessPiece implements Piece {

    private static int BLACK_STARTING_ROW = 6;
    private static int WHITE_STARTING_ROW = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isLegalStartingPosition() throws UnknownColorException {
        switch (getPieceColor()) {
            case BLACK:
                return xCoordinate == BLACK_STARTING_ROW && ChessBoard.isLegalBoardPosition(xCoordinate, yCoordinate);
            case WHITE:
                return xCoordinate == WHITE_STARTING_ROW && ChessBoard.isLegalBoardPosition(xCoordinate, yCoordinate);
            default:
                // This can't happen right now, but maybe in a future implementation a chess board can have different
                // sets of 2 colors. Maybe this should be a static class inside the chessboard? :D
                throw new UnknownColorException("Unknown color: " + pieceColor.name());
        }
    }

    @Override
    public void move(MovementType movementType, int newX, int newY) {
        try {
            if (!isLegalMove(newX, newY)) {
                System.out.println("An attempt for an illegal move for this color was made.");
                return;
            }
        } catch (UnknownColorException e) {
            System.out.println("Unknown color. " + e.getMessage());
            return;
        }
        switch (movementType) {
            case MOVE:
                chessBoard.movePiece(this, newX, newY);
                xCoordinate = newX;
                yCoordinate = newY;
                break;
            case CAPTURE:
                if (!isLegalCapture(newX, newY)) {
                    System.out.println("An attempt for an illegal capture was made.");
                    break;
                }
                chessBoard.capturePiece(this,newX, newY);
                xCoordinate = newX;
                yCoordinate = newY;
                break;
        }
    }

    public boolean isLegalMove(int newX, int newY) throws UnknownColorException {
        switch (pieceColor) {
            case WHITE:
                return newX > xCoordinate && (newX - xCoordinate) <= 2 && isLegalYAxisMove(newY);
            case BLACK:
                return newX < xCoordinate && (xCoordinate - newX) <= 2 && isLegalYAxisMove(newY);
            default:
                throw new UnknownColorException("Illegal move to X=" + newX + "; Y=" + newY);
        }
    }

    public boolean isLegalCapture(int newX, int newY) {
        return chessBoard.getPiece(newX, newY).getPieceColor() != pieceColor;
    }

    private boolean isLegalYAxisMove(int newY) {
        return newY == yCoordinate || newY == (yCoordinate +1 ) || newY == (yCoordinate -1);
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
