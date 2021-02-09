package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest extends TestCase {

    public static final int OUT_OF_BOUNDS_BORDER = -1;
    public static final int X_COORDINATE_6 = 6;
    public static final int Y_COORDINATE_3 = 3;
    public static final int X_COORDINATE_7 = 7;
    public static final int X_COORDINATE_5 = 5;
    public static final int X_COORDINATE_11 = 11;
    public static final int COORDINATE_0 = 0;
    public static final int Y_COORDINATE_9 = 9;
    public static final int MAX_BOARD_SQUARES = 8;
    private ChessBoard chessBoard;
    private PieceFactory factory;

    @Before
    public void setUp() {
        chessBoard = ChessBoard.getInstance();
        factory = PieceFactory.getInstance();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(MAX_BOARD_SQUARES, ChessBoard.MAX_BOARD_WIDTH);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(MAX_BOARD_SQUARES, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(COORDINATE_0, COORDINATE_0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(X_COORDINATE_5, X_COORDINATE_5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(X_COORDINATE_11, X_COORDINATE_5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(COORDINATE_0, Y_COORDINATE_9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(X_COORDINATE_11, COORDINATE_0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = chessBoard.isLegalBoardPosition(X_COORDINATE_5, OUT_OF_BOUNDS_BORDER);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Piece firstPawn = factory.createPiece(Piece.TypePiece.PAWN, PieceColor.BLACK);
        Piece secondPawn = factory.createPiece(Piece.TypePiece.PAWN, PieceColor.BLACK);
        chessBoard.addPiece(firstPawn, X_COORDINATE_6, Y_COORDINATE_3);
        chessBoard.addPiece(secondPawn, X_COORDINATE_6, Y_COORDINATE_3);
        assertEquals(X_COORDINATE_6, firstPawn.getXCoordinate());
        assertEquals(Y_COORDINATE_3, firstPawn.getYCoordinate());
        assertEquals(OUT_OF_BOUNDS_BORDER, secondPawn.getXCoordinate());
        assertEquals(OUT_OF_BOUNDS_BORDER, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        for (int i = COORDINATE_0; i < 10; i++) {
            Piece pawn = factory.createPiece(Piece.TypePiece.PAWN, PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            chessBoard.addPiece(pawn, X_COORDINATE_7 + row, i % ChessBoard.MAX_BOARD_WIDTH);
            if (row < 1) {
                assertEquals(X_COORDINATE_7 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            } else {
                assertEquals(OUT_OF_BOUNDS_BORDER, pawn.getXCoordinate());
                assertEquals(OUT_OF_BOUNDS_BORDER, pawn.getYCoordinate());
            }
        }
    }
}