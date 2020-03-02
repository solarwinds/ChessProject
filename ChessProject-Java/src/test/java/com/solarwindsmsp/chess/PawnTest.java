package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.testSubject.setChessBoard(chessBoard);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 7, 3);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 4, 3);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void test_Black_Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 6, 6, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 6, 5);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(5, testSubject.getYCoordinate());
    }

    @Test
    public void test_White_Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);
        whitePawn.setChessBoard(chessBoard);
        chessBoard.Add(whitePawn, 6, 1, PieceColor.WHITE);

        System.out.println(chessBoard);

        whitePawn.Move(MovementType.MOVE, 6, 2);

        assertEquals(6, whitePawn.getXCoordinate());
        assertEquals(2, whitePawn.getYCoordinate());

        System.out.println(chessBoard);
    }

    @Test
    public void test_Black_Pawn_Move_IllegalTargetCoordinates_Forward_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 0, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 6, -1);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(0, testSubject.getYCoordinate());
    }

    @Test
    public void test_White_Pawn_Move_IllegalTargetCoordinates_Forward_DoesNotMove() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);
        whitePawn.setChessBoard(chessBoard);
        chessBoard.Add(whitePawn, 6, 7, PieceColor.WHITE);
        whitePawn.Move(MovementType.MOVE, 6, 8);

        assertEquals(6, whitePawn.getXCoordinate());
        assertEquals(7, whitePawn.getYCoordinate());

    }

}