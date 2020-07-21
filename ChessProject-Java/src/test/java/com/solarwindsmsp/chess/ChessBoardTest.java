package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.ChessBoard.MAX_BOARD_INDEX;
import static org.junit.Assert.*;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardIndex_of_7() {
        assertEquals(7, MAX_BOARD_INDEX);
    }

    @Test
    public void testHas_MinBoardIndex_of_0() {
        assertEquals(0, ChessBoard.MIN_BOARD_INDEX);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);

        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);

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
    public void testIsLegalBoardPosition_False_For_Negative_X_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(-1, 5);

        assertFalse(isValidPosition);
    }

    @Test
    public void testAddPiece_Avoids_Duplicate_Positioning_Black_Color() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.addPiece(secondPawn, 6, 3, PieceColor.BLACK);

        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());

        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testAddPiece_Avoids_Duplicate_Positioning_White_Color() {
        Pawn firstPawn = new Pawn(PieceColor.WHITE);
        Pawn secondPawn = new Pawn(PieceColor.WHITE);

        testSubject.addPiece(firstPawn, 6, 3, PieceColor.WHITE);
        testSubject.addPiece(secondPawn, 6, 3, PieceColor.WHITE);

        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());

        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testAddPiece_Invalid_Board_Position() {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(pawn, 8, 3, PieceColor.BLACK);

        assertEquals(-1, pawn.getXCoordinate());
        assertEquals(-1, pawn.getYCoordinate());
    }

    @Test
    public void testAddPiece_Pawn_Color_Not_Matching() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        testSubject.addPiece(whitePawn, 6, 3, PieceColor.BLACK);

        assertEquals(-1, whitePawn.getXCoordinate());
        assertEquals(-1, whitePawn.getYCoordinate());
    }

    @Test
    public void testAddPiece_Limits_The_Number_Of_Pawns() {
        for (int y = 0; y < 10; y++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);

            testSubject.addPiece(pawn, 6, y, PieceColor.BLACK);

            if (y <= MAX_BOARD_INDEX) {
                assertEquals(6, pawn.getXCoordinate());
                assertEquals(y, pawn.getYCoordinate());
            } else {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testSpaceIsEmpty_True() {
        boolean isEmptySpace = testSubject.spaceIsEmpty(6, 3);

        assertTrue(isEmptySpace);
    }

    @Test
    public void testSpaceIsEmpty_False() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        testSubject.addPiece(pawn, 6, 3, PieceColor.BLACK);

        boolean isEmptySpace = testSubject.spaceIsEmpty(6, 3);

        assertFalse(isEmptySpace);
    }
}