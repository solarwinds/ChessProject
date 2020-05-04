package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.models.ChessBoard;
import com.solarwindsmsp.chess.models.ChessPiece;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.enums.PieceColor.BLACK;
import static com.solarwindsmsp.chess.enums.PieceType.PAWN;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.CHESS_BOARD_SIZE);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.CHESS_BOARD_SIZE);
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
    public void testAvoids_Duplicate_Positioning() {
        ChessPiece firstPawn = new ChessPiece(PAWN, BLACK);
        ChessPiece secondPawn = new ChessPiece(PAWN, BLACK);
        testSubject.addPiece(firstPawn, 6, 3);
        testSubject.addPiece(secondPawn, 6, 3);
        assertEquals(6, firstPawn.getCell().getXCoordinate());
        assertEquals(3, firstPawn.getCell().getYCoordinate());
        assertEquals(-1, secondPawn.getCell().getXCoordinate());
        assertEquals(-1, secondPawn.getCell().getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = 0; i < 10; i++) {
            ChessPiece pawn = new ChessPiece(PAWN, BLACK);
            int row = i / ChessBoard.CHESS_BOARD_SIZE;
            testSubject.addPiece(pawn, 6 + row, i % ChessBoard.CHESS_BOARD_SIZE);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getCell().getXCoordinate());
                assertEquals(i % ChessBoard.CHESS_BOARD_SIZE, pawn.getCell().getYCoordinate());
            } else {
                assertEquals(-1, pawn.getCell().getXCoordinate());
                Assert.assertEquals(-1, pawn.getCell().getYCoordinate());
            }
        }
    }
}
