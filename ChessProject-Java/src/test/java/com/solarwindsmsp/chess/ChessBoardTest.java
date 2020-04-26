package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.model.ChessBoard;
import com.solarwindsmsp.chess.model.ChessPiece;
import com.solarwindsmsp.chess.model.enums.PieceColor;
import com.solarwindsmsp.chess.model.enums.PieceType;
import com.solarwindsmsp.chess.util.Constants;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest extends TestCase {

    private ChessBoard testChessBoard;

    @Before
    public void setUp() throws Exception {
        testChessBoard = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, Constants.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, Constants.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        ChessPiece firstPawn = new ChessPiece(PieceColor.BLACK, PieceType.PAWN);
        ChessPiece secondPawn = new ChessPiece(PieceColor.BLACK, PieceType.PAWN);
        testChessBoard.addPiece(firstPawn, 6, 3);
        testChessBoard.addPiece(secondPawn, 6, 3);
        assertEquals(6, firstPawn.getxCoordinate());
        assertEquals(3, firstPawn.getyCoordinate());
        assertEquals(-1, secondPawn.getxCoordinate());
        assertEquals(-1, secondPawn.getyCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            ChessPiece pawn = new ChessPiece(PieceColor.BLACK, PieceType.PAWN);
            int row = i / Constants.MAX_BOARD_WIDTH;
            testChessBoard.addPiece(pawn, 6 + row, i % Constants.MAX_BOARD_WIDTH);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getxCoordinate());
                assertEquals(i % Constants.MAX_BOARD_WIDTH, pawn.getyCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getxCoordinate());
                Assert.assertEquals(-1, pawn.getyCoordinate());
            }
        }
    }
}