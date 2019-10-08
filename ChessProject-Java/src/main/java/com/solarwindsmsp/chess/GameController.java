package com.solarwindsmsp.chess;

public class GameController {
	private ChessBoard chessBoard;
	
	public GameController() {
		chessBoard = new ChessBoard();
	}
	
	public void newGame() {
		Cell[][] board = chessBoard.getBoard();
		try {
			addPawnsTo(board);
		} catch (CellOccupiedException e) {
			// this is at board setup, so this exception cannot happen here.  
		}
	}
	
	public void loadSavedGame() {
		//TODO load game position from database.
	}

	private void addPawnsTo(Cell[][] board) throws CellOccupiedException {
		for (int i=0; i< board[1].length; i++) {
			board[1][i].setPiece(new Pawn(PieceColor.WHITE));
		}
		for (int i=0; i< board[6].length; i++) {
			board[6][i].setPiece(new Pawn(PieceColor.BLACK));
		}
	}

	ChessBoard getChessBoard() {
		return chessBoard;
	}
}
