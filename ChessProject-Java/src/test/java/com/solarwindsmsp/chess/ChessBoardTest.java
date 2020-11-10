package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
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
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() throws IllegalBoardCoordinatesException {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.addPiece(firstPawn, 6, 3);
        testSubject.addPiece(secondPawn, 6, 3);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() throws IllegalBoardCoordinatesException {
        for (int i = 0; i < 10; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.addPiece(pawn, i % ChessBoard.MAX_BOARD_WIDTH, 6 + row);
            if (row < 1) {
                assertEquals(6 + row, pawn.getYCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getXCoordinate());
            } else {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testAll_Pawns() throws IllegalBoardCoordinatesException {
        for (int i = 0; i < ChessBoard.MAX_BOARD_WIDTH; i++) {
            Pawn blackPawn = new Pawn(PieceColor.BLACK);
            Pawn whitePawn = new Pawn(PieceColor.BLACK);

            testSubject.addPiece(blackPawn, i, 6);
            testSubject.addPiece(whitePawn, i, 1);

            assertEquals(6, blackPawn.getYCoordinate());
            assertEquals(i, blackPawn.getXCoordinate());

            assertEquals(1, whitePawn.getYCoordinate());
            assertEquals(i, whitePawn.getXCoordinate());
        }
    }
}