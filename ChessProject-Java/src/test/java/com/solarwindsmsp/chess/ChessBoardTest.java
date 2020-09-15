package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Pawn;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        Assert.assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        Assert.assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 5);
        // Test was faulty. Asserted true, however [11,5] is invalid as outside bounds of [0,0] through [7,7]
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 9);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 0);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAdd_Avoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.addPiece(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.addPiece(secondPawn, 6, 3, PieceColor.BLACK);
        Assert.assertEquals(6, firstPawn.getRowCoordinate());
        Assert.assertEquals(3, firstPawn.getColCoordinate());
        Assert.assertEquals(-1, secondPawn.getRowCoordinate());
        Assert.assertEquals(-1, secondPawn.getColCoordinate());
    }

    @Test
    public void testAdd_Limits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.addPiece(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                Assert.assertEquals(6 + row, pawn.getRowCoordinate());
                Assert.assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getColCoordinate());
            }
            else
            {
                Assert.assertEquals(-1, pawn.getRowCoordinate());
                Assert.assertEquals(-1, pawn.getColCoordinate());
            }
        }
    }
}