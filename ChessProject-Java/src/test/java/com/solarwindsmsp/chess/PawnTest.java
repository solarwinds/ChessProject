package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setup() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);

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
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_BlackColor() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 5, 3);

        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
        assertEquals(chessBoard, testSubject.getChessBoard());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_WhiteColor() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 2, 3);

        assertEquals(2, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
        assertEquals(chessBoard, whitePawn.getChessBoard());

    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Forward_2_Spaces() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 3, 3);

        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Backward_DoesNotMove() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 0, 3);

        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
    }
}