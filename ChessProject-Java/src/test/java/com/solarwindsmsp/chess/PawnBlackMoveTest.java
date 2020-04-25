package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnBlackMoveTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Back_InBlack_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 4, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 4, 5);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }
}