package com.solarwindsmsp.chess;

public abstract class Piece {
	private PieceColor colour;

	Piece(PieceColor pc){
		colour = pc;
	}
	
	abstract void move();

	public PieceColor getColour() {
		return colour;
	}
	
}
