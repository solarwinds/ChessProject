package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;//Only 7 lines? the tests pass and you're actually testing a constant for some reason
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
    	if(isLegalBoardPosition(xCoordinate, yCoordinate)&&pieces[xCoordinate][yCoordinate]==null) {
    	pieces[xCoordinate][yCoordinate]=pawn;
    	pawn.setXCoordinate(xCoordinate);
    	pawn.setYCoordinate(yCoordinate);
    	pawn.setPieceColor(pieceColor);
    	pawn.setChessBoard(this);
    	}else {
    		pawn.setXCoordinate(-1);
        	pawn.setYCoordinate(-1);
    	}
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	return xCoordinate<MAX_BOARD_WIDTH && 
    			xCoordinate>=0 &&
    			yCoordinate<MAX_BOARD_HEIGHT &&
    			yCoordinate>=0;
    }
}
