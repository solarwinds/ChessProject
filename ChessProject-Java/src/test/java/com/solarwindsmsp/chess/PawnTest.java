package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Tests for Pawn class
 */
public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn();
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() throws InvalidPlacementException {
        this.chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() throws InvalidPlacementException {
        this.chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() throws InvalidPlacementException, InvalidMoveException {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        try {
            testSubject.move(MovementType.MOVE, 7, 3);
            fail("Expected to raise exception, but didn't");
        } catch (InvalidMoveException e) {
        }
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() throws InvalidPlacementException, InvalidMoveException {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        try {
            testSubject.move(MovementType.MOVE, 4, 3);
            fail("Expected to raise exception, but didn't");
        } catch (InvalidMoveException e) {
        }
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() throws InvalidPlacementException, InvalidMoveException {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_toString() throws InvalidPlacementException {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        String eol = System.lineSeparator();
        String expected = String.format("Current X: 6%sCurrent Y: 3%sPiece Color: BLACK", eol, eol);
        assertEquals(expected, testSubject.toString());
    }
}
