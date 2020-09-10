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
import com.solarwindsmsp.chess.utils.TestUtils;

/**
 * Test class for {@link Pawn}.
 *
 */
@Category(JUnitTest.class)
public class PawnTest {

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
		testChessBoard.addPiece(testBlackPawn, TestUtils.TEST_INITIAL_BLACK_X_COORDINATE,
				TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		testChessBoard.addPiece(testWhitePawn, TestUtils.TEST_INITIAL_WHITE_X_COORDINATE,
				TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
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
		testBlackPawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		// Does't move because white plays first.
		assertEquals(TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveFirstMovementTwiceWhite() {
		testWhitePawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(3, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// Verify the history
		assertEquals(1, testChessBoard.getMoveHistory().size());
	}

	@Test
	public void moveFirstMovementWhite() {
		testWhitePawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(3, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// Verify the history
		assertEquals(1, testChessBoard.getMoveHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesBackward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 2, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 1, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(2, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 5, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		testBlackPawn.move(MovementType.MOVE, 6, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		assertEquals(5, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(2, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 2, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(2, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 5, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		testBlackPawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		assertEquals(5, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(2, testChessBoard.getMoveHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesInitialBackward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 0, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 7, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		assertEquals(TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesInitialForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		assertEquals(TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMoveHistory().size());
	}

	@Test
	public void moveIllegalCoordinatesOutBoard() {
		final int totalMovementToBeOut = 7;
		for (int x = 1; x <= totalMovementToBeOut; x++) {
			testWhitePawn.move(MovementType.MOVE, x + 1, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
			testBlackPawn.move(MovementType.MOVE, (TestUtils.TEST_INITIAL_BLACK_X_COORDINATE - x),
					TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		}
		assertEquals(ChessBoard.MAX_BOARD_WIDTH, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());

		assertEquals(0, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(12, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesToLeft() {

		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, 2);
		assertEquals(TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN.
		testBlackPawn.move(MovementType.MOVE, TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, 2);
		assertEquals(TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveIllegalCoordinatesToRight() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, 4);
		assertEquals(TestUtils.TEST_INITIAL_WHITE_X_COORDINATE, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN
		testBlackPawn.move(MovementType.MOVE, TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, 4);
		assertEquals(TestUtils.TEST_INITIAL_BLACK_X_COORDINATE, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(0, testChessBoard.getMoveHistory().size());

	}

	@Test
	public void moveLegalCoordinatesForward() {
		// WHITE PAWN.
		testWhitePawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		// Need to move Black before moving Whites.
		testBlackPawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		testWhitePawn.move(MovementType.MOVE, 4, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
		assertEquals(4, testWhitePawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE, testWhitePawn.getyCoordinate());
		// BLACK PAWN
		testBlackPawn.move(MovementType.MOVE, 3, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
		assertEquals(3, testBlackPawn.getxCoordinate());
		assertEquals(TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE, testBlackPawn.getyCoordinate());
		// Verify the history
		assertEquals(4, testChessBoard.getMoveHistory().size());

	}

	@Test(expected = UnsupportedOperationException.class)
	public void moveUnsupportedMovementTypeBlackPawn() {
		testWhitePawn.move(MovementType.CAPTURE, 4, TestUtils.TEST_INITIAL_BLACK_Y_COORDINATE);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void moveUnsupportedMovementTypeWhitePawn() {
		testWhitePawn.move(MovementType.CAPTURE, 3, TestUtils.TEST_INITIAL_WHITE_Y_COORDINATE);
	}

}