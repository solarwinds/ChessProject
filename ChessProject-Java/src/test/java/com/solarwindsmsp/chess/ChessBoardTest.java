package com.solarwindsmsp.chess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest {

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
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Piece firstPawn = new Pawn();
        Piece secondPawn = new Pawn();
        testSubject.add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.add(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            Piece pawn = new Pawn();
            int xIndex = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.add(pawn, 6 + xIndex, i % ChessBoard.MAX_BOARD_HEIGHT, PieceColor.BLACK);
            if (xIndex < 1) {
                assertEquals(6 + xIndex, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_HEIGHT, pawn.getYCoordinate());
            } else {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testEight_Pawns_Of_Each_Color_Are_Allowed() {
        PieceColor[] colors = {PieceColor.BLACK, PieceColor.WHITE};
        for (PieceColor color: colors) {
            int yIndex = (color == PieceColor.BLACK ? 6 : 1);
            for (int xIndex = 0; xIndex < 8; xIndex++) {
                Piece pawn = new Pawn();
                testSubject.add(pawn, xIndex, yIndex, color);
                assertEquals(xIndex, pawn.getXCoordinate());
                assertEquals(yIndex, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testBoard_Coordinates_Match_Piece_Coordinates_After_Move() {
        PieceColor[] colors = {PieceColor.BLACK, PieceColor.WHITE};
        for (PieceColor color: colors) {
            int xIndex = 6;
            int yIndex = (color == PieceColor.BLACK ? 6 : 1);
            int newYIndex = (color == PieceColor.BLACK ? 5 : 2);
            Piece pawn = new Pawn();

            testSubject.add(pawn, xIndex, yIndex, color);
            assertEquals(xIndex, pawn.getXCoordinate());
            assertEquals(yIndex, pawn.getYCoordinate());
            assertEquals(pawn, testSubject.pieceAt(xIndex, yIndex));

            pawn.move(MovementType.MOVE, xIndex, newYIndex);
            assertEquals(xIndex, pawn.getXCoordinate());
            assertEquals(newYIndex, pawn.getYCoordinate());
            assertNull(testSubject.pieceAt(xIndex, yIndex));
            assertEquals(pawn, testSubject.pieceAt(xIndex, newYIndex));
        }
    }

    @Test
    public void testPiece_Not_Allowed_On_Multiple_Boards() {
        Piece pawn = new Pawn();
        testSubject.add(pawn, 4, 2, PieceColor.WHITE);
        ChessBoard secondBoard = new ChessBoard();
        secondBoard.add(pawn, 4, 2, PieceColor.WHITE);
        assertEquals(testSubject, pawn.getChessBoard());
    }
}
