package com.solarwindsmsp.chess;

public class GameController {
	private static final int SEVENTH_ROW = 6;
	private static final int SECOND_ROW = 1;
	private ChessBoard chessBoard;
	
	public GameController() {
		chessBoard = new ChessBoard();
	}
	
	public void newGame() {
		try {
			addPawnsToBoard();
		} catch (CellOccupiedException e) {
			// this is at board setup, so this exception cannot happen here.  
		}
	}
	
	public void loadSavedGame() {
		//TODO load game position from database.
	}

	private void addPawnsToBoard() throws CellOccupiedException {
		Cell[][] board = chessBoard.getBoard();
		for (int i=0; i< board[SECOND_ROW].length; i++) {
			addPiece(new Pawn(PieceColor.WHITE), SECOND_ROW, i);
		}
		for (int i=0; i< board[SEVENTH_ROW].length; i++) {
			addPiece(new Pawn(PieceColor.BLACK), SEVENTH_ROW, i);
		}
	}
	
	private void addPiece(Piece piece, int xCoord, int yCoord) throws CellOccupiedException {
		chessBoard.add(piece, xCoord, yCoord);
	}

	ChessBoard getChessBoard() {
		return chessBoard;
	}
}
