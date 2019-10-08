package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameControllerTest {
	private GameController controller;
	private final static int WHITE_PAWN_ROW = 1;
	private final static int BLACK_PAWN_ROW = 6;
	private final static int NON_PAWN_ROW = 0;
	
	@Before
	public void before() {
		controller = new GameController();
		controller.newGame();
	}
	
	@Test
	public void testWhitePawnsSetup() {
		ChessBoard chessBoard = controller.getChessBoard();
		Cell[][] board = chessBoard.getBoard();
		for(int i=1; i< board[WHITE_PAWN_ROW].length; i++) {
			assertTrue("cell should be occupied", board[WHITE_PAWN_ROW][i].isOccupied());
			assertEquals("cell should contain a pawn", Pawn.class, board[WHITE_PAWN_ROW][i].getPiece().getClass());
			assertEquals("pawn should be white", PieceColor.WHITE, board[WHITE_PAWN_ROW][i].getPiece().getColour());
		}
	}
	
	@Test
	public void testBlackPawnsSetup() {
		ChessBoard chessBoard = controller.getChessBoard();
		Cell[][] board = chessBoard.getBoard();
		for(int i=1; i< board[BLACK_PAWN_ROW].length; i++) {
			assertTrue("cell should be occupied", board[BLACK_PAWN_ROW][i].isOccupied());
			assertEquals("cell should contain a pawn", Pawn.class, board[BLACK_PAWN_ROW][i].getPiece().getClass());
			assertEquals("pawn should be white", PieceColor.BLACK, board[BLACK_PAWN_ROW][i].getPiece().getColour());
		}
	}
	
	@Test
	public void testFirstRowHasNoPawns() {
		ChessBoard chessBoard = controller.getChessBoard();
		Cell[][] board = chessBoard.getBoard();
		for(int i=1; i< board[NON_PAWN_ROW].length; i++) {
			assertFalse("cell should not be occupied", board[NON_PAWN_ROW][i].isOccupied());
			assertNull("cell should not contain a pawn", board[NON_PAWN_ROW][i].getPiece());
		}
	}
}
