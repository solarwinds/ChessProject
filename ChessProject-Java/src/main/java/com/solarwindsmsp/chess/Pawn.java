package com.solarwindsmsp.chess;

public class Pawn extends Piece{

    public static final int ONE_SQUARE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (ChessBoard.getInstance().isLegalBoardPosition(newX, newY)) {
            if (movementType == MovementType.MOVE){
                if (this.getXCoordinate() == newX) {
                    setXCoordinate(newX);
                    setYCoordinate(newY);
                }
            } else{
                if (isValidCaptureLeft(newX, newY) || isValidCaptureRight(newX, newY)) {
                    setXCoordinate(newX);
                    setYCoordinate(newY);
                } else {
                    throw new UnsupportedOperationException("Capture conditions not met");
                }
            }
        }
    }

    private boolean isValidCaptureLeft(int newX, int newY) {
        return getXCoordinate() - ONE_SQUARE == newX && getYCoordinate() + ONE_SQUARE == newY;
    }

    private boolean isValidCaptureRight(int newX, int newY) {
        return getXCoordinate() + ONE_SQUARE == newX && getYCoordinate() + ONE_SQUARE == newY;
    }

}
