package com.solarwindsmsp.chess;

class Cell {
	private int xPosition;
	private int yPosition;
	private Piece piece;
	
	Cell(int x, int y){
		xPosition = x; 
		yPosition = y;
	}

	boolean isOccupied() {
		return piece != null;
	}

	void setPiece(Piece thePiece) throws CellOccupiedException {
		if(piece != null) {
			throw new CellOccupiedException();
		} else {
			this.piece = thePiece;
		}
	}
	
	public Piece getPiece() {
		return piece;
	}

	void clear() {
		piece = null;
	}

	public int getxPosition() {
		return xPosition;
	}
	
	public int getyPosition() {
		return yPosition;
	}
}
