package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exceptions.BoardPositionNotEmptyException;
import com.solarwindsmsp.chess.exceptions.MaximumNumberOfPiecesReached;
import com.solarwindsmsp.chess.pieces.Pawn;
import com.solarwindsmsp.chess.pieces.Piece;
import com.solarwindsmsp.chess.pieces.PieceColor;

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
    public void testHas_BoardWidth_of_8() {
        assertEquals(8, ChessBoard.BOARD_HEIGHT);
    }

    @Test
    public void testHas_BoardHeight_of_8() {
        assertEquals(8, ChessBoard.BOARD_HEIGHT);
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
    public void testIsEmptyBoardPosition_invalidPosition() {
        boolean isValidPosition = testSubject.isEmptyBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsEmptyBoardPosition_positionNotEmpty_invalid() throws Exception {
        testSubject.addPiece(new Pawn(PieceColor.WHITE), 2, 3);
        boolean isValidPosition = testSubject.isEmptyBoardPosition(2, 3);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsEmptyBoardPosition_positionEmpty_valid() {
        boolean isValidPosition = testSubject.isEmptyBoardPosition(2, 3);
        assertTrue(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() throws Exception {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(firstPawn, 6, 3);

        try {
            testSubject.addPiece(secondPawn, 6, 3);
        } catch (BoardPositionNotEmptyException e) {
            System.out.println(e);
        }

        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() throws Exception {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.BOARD_WIDTH;

            try {
                testSubject.addPiece(pawn, 6 + row, i % ChessBoard.BOARD_WIDTH);
            } catch (MaximumNumberOfPiecesReached e) {
                System.out.println(e);
            }

            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void test_getCurrentNumberOfPieces() throws Exception{

        testSubject.addPiece(new Pawn(PieceColor.BLACK), 3, 2);
        testSubject.addPiece(new Pawn(PieceColor.BLACK), 4, 2);
        testSubject.addPiece(new Pawn(PieceColor.BLACK), 5, 2);
        testSubject.addPiece(new Pawn(PieceColor.BLACK), 6, 2);

        testSubject.addPiece(new Pawn(PieceColor.WHITE), 0, 2);
        testSubject.addPiece(new Pawn(PieceColor.WHITE), 1, 2);

        assertEquals(4, testSubject.getCurrentNumberOfPieces(Pawn.class, PieceColor.BLACK));
        assertEquals(2, testSubject.getCurrentNumberOfPieces(Pawn.class, PieceColor.WHITE));
    }

    @Test
    public void test_commitMove() throws Exception {
        Piece piece = new Pawn(PieceColor.WHITE);
        testSubject.addPiece(piece, 1, 1);

        testSubject.commitMove(piece, 1, 2);

        assertNull(testSubject.getPieces()[1][1]);
        assertEquals(piece, testSubject.getPieces()[1][2]);

        assertEquals(piece.getXCoordinate(), 1);
        assertEquals(piece.getYCoordinate(), 2);
        assertEquals(testSubject, piece.getChessBoard());
    }
}