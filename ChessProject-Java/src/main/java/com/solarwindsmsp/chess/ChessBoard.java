package com.solarwindsmsp.chess;

public class ChessBoard {

	public static int MAX_BOARD_WIDTH = 7;
	public static int MAX_BOARD_HEIGHT = 7;

	private Pawn[][] pieces;

	public ChessBoard() {
		pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

	}

	
	/* Changes done :
	 * Implemented following methods : 
	 * 1. Add()
	 * 2. IsLegalBoardPosition() 
	 * */
	
	public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
		if(pieces[xCoordinate][yCoordinate] != null){
			pawn.setXCoordinate(-1);
			pawn.setYCoordinate(-1);
		}
		else{
			pieces[xCoordinate][yCoordinate]=pawn;
			pawn.setXCoordinate(xCoordinate);
			pawn.setYCoordinate(yCoordinate);
		}
	}

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
		if( xCoordinate < 0 || yCoordinate < 0 ){
			return false;
		}
		if( xCoordinate > 7 || yCoordinate > 7 ){
			return false;
		}
		return true;
	}
}
