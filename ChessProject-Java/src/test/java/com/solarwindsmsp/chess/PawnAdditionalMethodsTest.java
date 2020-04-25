package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnAdditionalMethodsTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testToString_outOffTheBoard() {
        String toString = testSubject.toString();
        assertEquals("\nCurrent X: -1Current Y: -1Piece Color: BLACK", toString);

    }

    @Test
    public void testToString_onTheBoard() {
        chessBoard.Add(testSubject, 1, 1, PieceColor.BLACK);
        String toString = testSubject.toString();
        assertEquals("\nCurrent X: 1Current Y: 1Piece Color: BLACK", toString);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_capture() {
        chessBoard.Add(testSubject, 1, 1, PieceColor.BLACK);
        testSubject.allowCaptureAt(1, 2);
    }

}