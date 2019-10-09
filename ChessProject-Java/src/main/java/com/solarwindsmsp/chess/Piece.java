package com.solarwindsmsp.chess;

public abstract class Piece {
	private PieceColor colour;
	protected Cell cell;

	Piece(PieceColor pc){
		colour = pc;
	}
	
	public PieceColor getColour() {
		return colour;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell aCell) {
		this.cell = aCell;
	}

	abstract void moveToCell(Cell newCell) throws IllegalMoveException;
	
}
