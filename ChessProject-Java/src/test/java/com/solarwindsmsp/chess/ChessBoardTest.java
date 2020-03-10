package com.solarwindsmsp.chess;

import javafx.util.Pair;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;


/**
 * Tests checking the behaviour of the Chess Board.
 */
public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;
    private Map<Pair<Integer, Integer>, Piece> pieces;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
        pieces = testSubject.getMapOfPieces();
        pieces.clear();
        Pawn.numberOfWhitePawns = 0;
        Pawn.numberOfBlackPawns = 0;
    }

    /**
     * Changed to reflect the new board size.
     */
    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    /**
     * Changed to reflect the new board size.
     */
    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    /**
     * Changed to expect false to account for the change to 1-8 coordinates.
     */
    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_1_Y_equals_1() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(1, 1);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_8_Y_equals_8() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(8, 8);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, -1);
        assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() throws Exception {
        Pawn firstPawn = new Pawn(PieceColor.BLACK, 6, 3, testSubject);
        Pawn secondPawn = new Pawn(PieceColor.BLACK, 6, 3, testSubject);
        assertTrue(testSubject.add(firstPawn));
        Assertions.assertThrows(Exception.class, () -> testSubject.add(secondPawn));
        assertEquals(6, pieces.get(new Pair<>(firstPawn.xCoordinate, firstPawn.yCoordinate)).getXCoordinate());
        assertEquals(3, pieces.get(new Pair<>(firstPawn.xCoordinate, firstPawn.yCoordinate)).getYCoordinate());
    }

    /**
     * Modified to reflect the new implementation.
     *
     * @throws Exception
     */
    @Test
    public void testLimits_The_Number_Of_Pawns() throws Exception {
        int row = 6;
        for (int i = 1; i <= 8; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK, row, i, testSubject);
            testSubject.add(pawn);
            assertEquals(row, pawn.getXCoordinate());
            assertEquals(i, pawn.getYCoordinate());
        }

        row = 7;
        for (int i = 1; i <= 2; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK, row, i, testSubject);
            Assertions.assertThrows(Exception.class, () -> testSubject.add(pawn));
            assertEquals(8, pieces.size());
        }
    }
}