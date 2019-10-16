package com.solarwindsmsp.chess.chessboard;

import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_HEIGHT;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_WIDTH;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(0, 0);

        assertFalse(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_X_equals_5_Y_equals_5() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(5, 5);

        assertFalse(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_11_Y_equals_5() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(11, 5);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_0_Y_equals_9() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(0, 9);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(11, 0);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_For_Negative_X_Values() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(-1, 5);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(5, -1);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testAdd_False_For_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.add(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / MAX_BOARD_WIDTH;
            testSubject.add(pawn, 6 + row, i % MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
}