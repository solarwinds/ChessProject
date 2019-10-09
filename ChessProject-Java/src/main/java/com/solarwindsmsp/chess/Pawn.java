package com.solarwindsmsp.chess;

public class Pawn extends Piece {

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
	}

	public void Move(MovementType movementType, int newX, int newY) {
		throw new UnsupportedOperationException("Need to implement Pawn.Move()") ;
	}

	public String getDetails() {
		String colour = this.getColour().toString();
		if(cell != null) {
			return cell.positionAsString() + "Colour: " + colour;
		}
		return colour;
	}

	@Override
	void moveToCell(Cell newCell) throws IllegalMoveException {
		if(moveIsLegal(newCell)) {
			this.cell = newCell;
		} else throw new IllegalMoveException();
	}

	/*
	 * A pawn may only move in the y axis.
	 * If it is black it may only move down the board. If white, it may only move up. 
	 */
	private boolean moveIsLegal(Cell newCell) {
		boolean legal = false;
		if(sameXposition(newCell)) {
			legal = legalChangeInYposition(newCell);
		}
		return legal;
	}

	private boolean legalChangeInYposition(Cell newCell) {
		int yDelta = this.cell.getyPosition() - newCell.getyPosition();
		boolean legal = false;
		switch (this.getColour()) {
		case WHITE:
			if(yDelta == -1) {
				legal = true;
			}
			break;
		case BLACK:
			if(yDelta == 1) {
				legal = true;
			}
			break;
		default:
			break;
		}
		return legal;
	}

	private boolean sameXposition(Cell newCell) {
		return this.cell.getxPosition() == newCell.getxPosition();
	}

}
