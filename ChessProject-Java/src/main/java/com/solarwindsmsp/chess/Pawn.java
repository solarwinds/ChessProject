package com.solarwindsmsp.chess;

public class Pawn {

	private ChessBoard chessBoard;
	private int xCoordinate;
	private int yCoordinate;
	private PieceColor pieceColor;

	public Pawn(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
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

	public void setPieceColor(PieceColor value) {
		pieceColor = value;
	}

	public void move(MovementType movementType, int newX, int newY) {
		if (chessBoard.isLegalBoardPosition(newX, newY)) {
			if (getPieceColor() == PieceColor.BLACK) {
				moveBlackPawn(movementType, newX, newY);
			} else {
				moveWhitePawn(movementType, newX, newY);
			}
		}

	}

	private void moveWhitePawn(MovementType movementType, int newX, int newY) {
		if (movementType == MovementType.MOVE) {

			if ((yCoordinate == newY - 1) || ((yCoordinate == 1) && (newY == 3))) {
				setYCoordinate(newY);
			}
		}

	}

	private void moveBlackPawn(MovementType movementType, int newX, int newY) {
		if (movementType == MovementType.MOVE) {

			if ((yCoordinate == newY + 1) || ((yCoordinate == 6) && (newY == 4))) {
				setYCoordinate(newY);
			}
		}
	}

	@Override
	public String toString() {
		return getCurrentPositionAsString();
	}

	protected String getCurrentPositionAsString() {
		String eol = System.lineSeparator();
		return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate,
				pieceColor);
	}
}
