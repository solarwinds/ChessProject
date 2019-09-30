package com.solarwindsmsp.chess;

public abstract class ChessPiece {

	// Initialise to invalid value
	// TODO: Consider if pieces need to store their co-ords at all.
	// Information is redundant as encoded in their position on board.
	// Pieces are "nearly" stateless, (apart from colour) - simplifies testing.

	// Reference to ChessBoard has been removed to avoid circular dependency on containing type.
	// isValidMove added to allow "try before you buy" for callers.
	private int xCoordinate = -1;
	private int yCoordinate = -1;
	protected PieceColor pieceColor;

	public ChessPiece(PieceColor value) {
		super();
		this.pieceColor = value;
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

	@Override
	public String toString() {
	    return CurrentPositionAsString();
	}

	protected int toRelativeLeftSquares(int newX) {
		// Num squares to the left from point of view of black or white
		if (pieceColor == PieceColor.BLACK) {
			return getXCoordinate() - newX;
		} else {
			return newX - getXCoordinate();			
		}
	}

	protected int toRelativeForwardSquares(int newY) {
		// num squares forward from point of view of black or white
		if (pieceColor == PieceColor.BLACK) {
			return getYCoordinate() - newY;
		} else {
			return newY - getYCoordinate();			
		}
	}
	
	protected String CurrentPositionAsString() {
	    String eol = System.lineSeparator();
	    return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
	}

	// Piece type-specific methods
	
	public abstract String toSymbol();

	public abstract boolean isValidMove(MovementType movementType, int newX, int newY);
	
	public abstract void Move(MovementType movementType, int newX, int newY);
	
	public abstract int getMaxPiecesPerColor();
	
}