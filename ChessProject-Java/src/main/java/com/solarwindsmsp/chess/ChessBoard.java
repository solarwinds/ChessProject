package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate) {
    	if(isLegalAdd(xCoordinate, yCoordinate, pawn.getPieceColor())) {
    		pieces[xCoordinate][yCoordinate] = pawn;
    		pawn.setXCoordinate(xCoordinate);
    		pawn.setYCoordinate(yCoordinate);
    	}
    }
    
    public void movePiece(Pawn pawn, int xCoordinate, int yCoordinate) {
    	if(isLegalBoardPosition(xCoordinate, yCoordinate)) {
    		pieces[pawn.getXCoordinate()][pawn.getYCoordinate()] = null;
    		pieces[xCoordinate][yCoordinate] = pawn;
    		pawn.setXCoordinate(xCoordinate);
    		pawn.setYCoordinate(yCoordinate);
    	}
    }

	public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
		return xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH && yCoordinate >= 0 && yCoordinate < MAX_BOARD_HEIGHT;
	}
    
	private boolean isLegalAdd(int xCoordinate, int yCoordinate, PieceColor pieceColor) {
		// check coordinates are valid on the board & not yet filled
		if (isLegalBoardPosition(xCoordinate, yCoordinate) && pieces[xCoordinate][yCoordinate] == null) {
			if (pieceColor == PieceColor.WHITE && (xCoordinate == 0 || xCoordinate == 1)) {
				return true;
			}
			if (pieceColor == PieceColor.BLACK && (xCoordinate == MAX_BOARD_HEIGHT - 1 || xCoordinate == MAX_BOARD_HEIGHT - 2)) {
				return true;
			}
		}
		return false;
	}
}
