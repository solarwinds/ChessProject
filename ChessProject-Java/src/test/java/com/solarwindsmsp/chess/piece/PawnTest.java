package com.solarwindsmsp.chess.piece;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.IChessBoard;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;
import com.solarwindsmsp.chess.suite.category.JUnitTest;

/**
 * Test class for {@link Pawn}.
 *
 */
@Category(JUnitTest.class)
public class PawnTest {

	/** Initial X coordinate for a Black Pawn. */
	private static final int INITIAL_BLACK_X_COORDINATE = Pawn.INITIAL_BLACK_X_COORDINATE;

	/** Initial Y coordinate for a Black Pawn for testing. */
	private static final int INITIAL_BLACK_Y_COORDINATE = 3;

	/** Initial X coordinate for a White Pawn. */
	private static final int INITIAL_WHITE_X_COORDINATE = Pawn.INITIAL_WHITE_X_COORDINATE;

	/** Initial Y coordinate for a white Pawn for testing. */
	private static final int INITIAL_WHITE_Y_COORDINATE = 4;

	/** CheckBoard for testing. */
	protected IChessBoard testChessBoard = new ChessBoard();

	/** Factory of piece to get a piece. */
	protected final PieceFactory pieceFactory = new PieceFactory();

	/** White Pawn to be tested. */
	private IPiece testWhitePawn;

	/** Black Pawn to be tested. */
	private IPiece testBlackPawn;

	@Before
	public void setUp() {
		// New pieces are required to be used in each test
		testWhitePawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.WHITE);
		testBlackPawn = pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK);
		testChessBoard.addPiece(testBlackPawn, INITIAL_BLACK_X_COORDINATE, INITIAL_BLACK_Y_COORDINATE);
		testChessBoard.addPiece(testWhitePawn, INITIAL_WHITE_X_COORDINATE, INITIAL_WHITE_Y_COORDINATE);
	}

	/**
	 * To reuse the same instance instead of creating multiple instances during the
	 * (Before - setUp). That could end up, having memory issues. If the test is
	 * huge and the GC is not good enough.
	 *
	 * @throws Exception if something wrong happens.
	 */
	@After
	public void tearDown() throws Exception {
		testChessBoard.reset();
	}

	@Test
	public void moveFirstMovementBlack() {
		testBlackPawn.move(MovementType.MOVE, 4, INITIAL_BLACK_Y_COORDINATE);
		// Does't move because white plays first.
		assertEquals(INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveFirstMovementTwiceWhite() {
		testWhitePawn.move(MovementType.MOVE, 3, INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(3, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// Verify the history
		assertEquals(1, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void moveFirstMovementWhite() {
		testWhitePawn.move(MovementType.MOVE, 3, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(3, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// Verify the history
		assertEquals(1, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesBackward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 2, INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 1, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(2, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 5, INITIAL_BLACK_Y_COORDINATE);
		testBlackPawn.move(MovementType.MOVE, 6, INITIAL_BLACK_Y_COORDINATE);
		assertEquals(5, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(2, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 2, INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(2, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 5, INITIAL_BLACK_Y_COORDINATE);
		testBlackPawn.move(MovementType.MOVE, 3, INITIAL_BLACK_Y_COORDINATE);
		assertEquals(5, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(2, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesInitialBackward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 0, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 7, INITIAL_BLACK_Y_COORDINATE);
		assertEquals(INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesInitialForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 4, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 3, INITIAL_BLACK_Y_COORDINATE);
		assertEquals(INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesOutBoard() {
		final int totalMovementToBeOut = 7;
		for (int x = 1; x <= totalMovementToBeOut; x++) {
			testWhitePawn.move(MovementType.MOVE, x + 1, INITIAL_WHITE_Y_COORDINATE);
			testBlackPawn.move(MovementType.MOVE, (INITIAL_BLACK_X_COORDINATE - x), INITIAL_BLACK_Y_COORDINATE);
		}
		assertEquals(ChessBoard.MAX_BOARD_WIDTH, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());

		assertEquals(0, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(12, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesToLeft() {

		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, INITIAL_WHITE_X_COORDINATE, 2);
		assertEquals(INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, INITIAL_BLACK_X_COORDINATE, 2);
		assertEquals(INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesToRight() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, INITIAL_WHITE_X_COORDINATE, 4);
		assertEquals(INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN
		testBlackPawn.move(MovementType.MOVE, INITIAL_BLACK_X_COORDINATE, 4);
		assertEquals(INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMovementHistory().size());

	}

	@Test
	public void moveLegalCoordinatesForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 3, INITIAL_WHITE_Y_COORDINATE);
		// Need to move Black before moving Whites.
		testBlackPawn.move(MovementType.MOVE, 4, INITIAL_BLACK_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, INITIAL_WHITE_Y_COORDINATE);
		assertEquals(4, testWhitePawn.getxCoordinate());
		assertEquals(INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN
		testBlackPawn.move(MovementType.MOVE, 3, INITIAL_BLACK_Y_COORDINATE);
		assertEquals(3, testBlackPawn.getxCoordinate());
		assertEquals(INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(4, testChessBoard.getMovementHistory().size());

	}

	@Test(expected = UnsupportedOperationException.class)
	public void moveUnsupportedMovementTypeBlackPawn() {
		testWhitePawn.move(MovementType.CAPTURE, 4, INITIAL_BLACK_Y_COORDINATE);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void moveUnsupportedMovementTypeWhitePawn() {
		testWhitePawn.move(MovementType.CAPTURE, 3, INITIAL_WHITE_Y_COORDINATE);
	}

}