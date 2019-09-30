package com.solarwindsmsp.chess;

public class Pawn extends ChessPiece {

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
    }

    @Override
	public void Move(MovementType movementType, int newX, int newY) {
    	if (isValidMove(movementType, newX, newY)) {
    		setXCoordinate(newX);
    		setYCoordinate(newY);
    	}
    	return;
    }

    @Override
	public String toSymbol() {
    	// Use convention of uppercase for black, lowercase for white to allow single character to be used for piece.
    	return getPieceColor() == PieceColor.BLACK? "P" : "p";
    }

	@Override
	public boolean isValidMove(MovementType movementType, int newX, int newY) {
		boolean isValid = false;
		int numSquaresLeft = toRelativeLeftSquares(newX);
		int numSquaresForward = toRelativeForwardSquares(newY);
		
    	if (movementType == MovementType.MOVE) {
    		// TODO: Consider pawns allowed to move two spaces on first move. Initial version, always one space as per spec.
    		isValid = numSquaresForward == 1 && numSquaresLeft == 0;
    	} else {
    		// Capture is diagonal move forward to left or right
    		isValid = numSquaresForward == 1 && (numSquaresLeft == 1 || numSquaresLeft == -1);
    	}
		
    	return isValid;
	}

	@Override
	public int getMaxPiecesPerColor() {
		return 8;
	}
}
