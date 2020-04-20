package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.movement.MovementType;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.Constants.MAX_BOARD_HEIGHT_INDEX;
import static com.solarwindsmsp.chess.Constants.MAX_BOARD_WIDTH_INDEX;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT_INDEX);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT_INDEX);
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
        //correcting this assert, as coordinate 11 is out of the board
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
    public void testAvoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
        Pawn secondPawn = new Pawn(testSubject, PieceColor.BLACK);
        testSubject.addPiece(firstPawn, 6, 3);
        testSubject.addPiece(secondPawn, 6, 3);
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
            Pawn pawn = new Pawn(testSubject, PieceColor.BLACK);
            int row = i / (MAX_BOARD_WIDTH_INDEX +1);
            testSubject.addPiece(pawn, 6 + row, i % (MAX_BOARD_WIDTH_INDEX + 1));
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % (MAX_BOARD_WIDTH_INDEX +1), pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
}