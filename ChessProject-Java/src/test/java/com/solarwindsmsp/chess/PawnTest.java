package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.chessBoard.setStrategyFactory(new MovementStrategyFactory());
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

}