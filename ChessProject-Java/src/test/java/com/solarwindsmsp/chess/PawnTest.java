package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;
    private Pawn testSubjectW;
    private Pawn testSubjectB;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
        this.testSubjectB = new Pawn(PieceColor.BLACK);
        this.testSubjectW = new Pawn(PieceColor.WHITE);
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

    @Test
    public void testPawn_Move_White_IllegalCoordinates_Backwards() {
        chessBoard.addPiece(testSubjectW, 4, 1, PieceColor.WHITE);
        testSubjectW.move(MovementType.MOVE, 4, 0);
        assertEquals(4, testSubjectW.getXCoordinate());
        assertEquals(1, testSubjectW.getYCoordinate());
    }

    @Test
    public void testPawn_Move_Black_IllegalCoordinates_Backwards() {
        chessBoard.addPiece(testSubjectB, 5, 4, PieceColor.BLACK);
        testSubjectB.move(MovementType.MOVE, 5, 5);
        assertEquals(5, testSubjectB.getXCoordinate());
        assertEquals(4, testSubjectB.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_LegalCoordinates() {
        chessBoard.addPiece(testSubjectW, 4, 1, PieceColor.WHITE);
        chessBoard.addPiece(testSubjectB, 3, 2, PieceColor.BLACK);
        testSubjectW.move(MovementType.CAPTURE, 3, 2);
        assertEquals(3, testSubjectW.getXCoordinate());
        assertEquals(2, testSubjectW.getYCoordinate());
        assertEquals(null, chessBoard.getPiece(4, 1));
    }

    @Test
    public void testPawn_Capture_IllegalCoordinates() {
        chessBoard.addPiece(testSubjectW, 4, 1, PieceColor.WHITE);
        chessBoard.addPiece(testSubjectB, 4, 2, PieceColor.BLACK);
        testSubjectW.move(MovementType.CAPTURE, 4, 2);
        assertEquals(4, testSubjectW.getXCoordinate());
        assertEquals(1, testSubjectW.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_IllegalColor() {
        chessBoard.addPiece(testSubject, 4, 1, PieceColor.BLACK);
        chessBoard.addPiece(testSubjectB, 3, 2, PieceColor.BLACK);
        testSubject.move(MovementType.CAPTURE, 3, 2);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(1, testSubject.getYCoordinate());
    }

}
