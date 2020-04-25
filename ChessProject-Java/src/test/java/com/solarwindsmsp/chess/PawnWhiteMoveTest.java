package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnWhiteMoveTest {
    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        assertEquals(4, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        assertEquals(1, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        testSubject.Move(MovementType.MOVE, 5, 1);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(1, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        testSubject.Move(MovementType.MOVE, 5, 1);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(1, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Back_DoesNotMove() {
        chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        testSubject.Move(MovementType.MOVE, 4, 0);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(1, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 4, 1, PieceColor.WHITE);
        testSubject.Move(MovementType.MOVE, 4, 2);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

}
