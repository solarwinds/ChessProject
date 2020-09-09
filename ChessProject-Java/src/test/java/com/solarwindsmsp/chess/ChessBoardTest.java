package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.pieces.IPiece;
import com.solarwindsmsp.chess.pieces.Pawn;
import com.solarwindsmsp.chess.pieces.attributes.PieceColor;
import com.solarwindsmsp.chess.pieces.attributes.PieceType;
import com.solarwindsmsp.chess.utils.TestUtils;

/**
 * Test class for {@link ChessBoard}.
 *
 */
public class ChessBoardTest extends TestUtils {

	@Before
	public void setUp() throws Exception {
		testChessBoard = new ChessBoard();
	}

	@Test
	public void addPieceAvoidDuplicatePositioning() {
		final IPiece firstPawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK);
		final IPiece secondPawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK);
		testChessBoard.addPiece(firstPawn, Pawn.INITIAL_BLACK_X_COORDINATE, 3);
		testChessBoard.addPiece(secondPawn, Pawn.INITIAL_BLACK_X_COORDINATE, 3);
		assertEquals(Pawn.INITIAL_BLACK_X_COORDINATE, firstPawn.getxCoordinate());
		assertEquals(3, firstPawn.getyCoordinate());
		assertEquals(-1, secondPawn.getxCoordinate());
		assertEquals(-1, secondPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void addPieceLimitNumberOfBlackPawns() {
		for (int i = 0; i < 10; i++) {
			final IPiece pawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK);
			final int row = i / (ChessBoard.MAX_BOARD_HEIGHT + 1);
			testChessBoard.addPiece(pawn, Pawn.INITIAL_BLACK_X_COORDINATE + row, i % (ChessBoard.MAX_BOARD_WIDTH + 1));
			if (row < 1) {
				assertEquals(Pawn.INITIAL_BLACK_X_COORDINATE + row, pawn.getxCoordinate());
				assertEquals(i % (ChessBoard.MAX_BOARD_HEIGHT + 1), pawn.getyCoordinate());
			} else {
				assertEquals(-1, pawn.getxCoordinate());
				assertEquals(-1, pawn.getyCoordinate());
			}
		}
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void addPieceLimitNumberOfWhitePawns() {

		for (int i = 0; i < 10; i++) {
			final IPiece pawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.WHITE);
			final int row = i / (ChessBoard.MAX_BOARD_HEIGHT + 1);
			testChessBoard.addPiece(pawn, Pawn.INITIAL_WHITE_X_COORDINATE + row, i % (ChessBoard.MAX_BOARD_WIDTH + 1));
			if (row < 1) {
				assertEquals(Pawn.INITIAL_WHITE_X_COORDINATE + row, pawn.getxCoordinate());
				assertEquals(i % (ChessBoard.MAX_BOARD_HEIGHT + 1), pawn.getyCoordinate());
			} else {
				assertEquals(-1, pawn.getxCoordinate());
				assertEquals(-1, pawn.getyCoordinate());
			}
		}
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void addPiecePawnIllegalXYCoordinate() {
		final IPiece whitePawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.WHITE);
		final IPiece blackPawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK);
		// WHITE PAWN (X Coordinate).
		testChessBoard.addPiece(whitePawn, 0, 3);
		assertEquals(-1, whitePawn.getxCoordinate());
		assertEquals(-1, whitePawn.getyCoordinate());
		// WHITE PAWN (Y Coordinate).
		testChessBoard.addPiece(whitePawn, 1, 8);
		assertEquals(-1, whitePawn.getxCoordinate());
		assertEquals(-1, whitePawn.getyCoordinate());
		// BLACK PAWN (X Coordinate)
		testChessBoard.addPiece(blackPawn, 7, 3);
		assertEquals(-1, whitePawn.getxCoordinate());
		assertEquals(-1, whitePawn.getyCoordinate());
		// BLACK PAWN (Y Coordinate)
		testChessBoard.addPiece(blackPawn, 6, -1);
		assertEquals(-1, whitePawn.getxCoordinate());
		assertEquals(-1, whitePawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void checkMaxHeightSizeBoard() {
		assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
	}

	@Test
	public void checkMaxWidthSizeBoard() {
		assertEquals(7, ChessBoard.MAX_BOARD_WIDTH);
	}

	@Test
	public void isLegalBoardPosition() {
		for (int x = 0; x <= ChessBoard.MAX_BOARD_HEIGHT; x++) {
			for (int y = 0; y <= ChessBoard.MAX_BOARD_WIDTH; y++) {
				final boolean isValidPosition = testChessBoard.isLegalBoardPosition(x, y);
				assertTrue(isValidPosition);
			}
		}
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void isLegalBoardPositionForHigtherCoodinatesThanMaxBoard() {
		boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, 8);
		assertFalse(isValidPosition);
		isValidPosition = testChessBoard.isLegalBoardPosition(8, 7);
		assertFalse(isValidPosition);
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void isLegalBoardPositionForNegativeCoordinates() {
		boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, -1);
		assertFalse(isValidPosition);
		isValidPosition = testChessBoard.isLegalBoardPosition(-1, 3);
		assertFalse(isValidPosition);
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

}