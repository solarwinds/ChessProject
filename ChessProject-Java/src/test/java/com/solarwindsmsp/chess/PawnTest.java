package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubjectBlack;
    private Pawn testSubjectWhite;
    private Pawn testSubjectWhiteAux;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubjectBlack = new Pawn(PieceColor.BLACK);
        this.testSubjectWhite = new Pawn(PieceColor.WHITE);
        this.testSubjectWhiteAux = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        assertEquals(6, testSubjectBlack.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        testSubjectBlack.move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubjectBlack.getXCoordinate());
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        testSubjectBlack.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubjectBlack.getXCoordinate());
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testBlackPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        testSubjectBlack.move(MovementType.MOVE, 4, 3);
        assertEquals(4, testSubjectBlack.getXCoordinate());
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE, true);
        testSubjectWhite.move(MovementType.MOVE, 2, 3);
        assertEquals(2, testSubjectWhite.getXCoordinate());
        assertEquals(3, testSubjectWhite.getYCoordinate());
    }

    @Test
    public void testBlackPawn_Move_IllegalCoordinates_Backwards_UpdatesCoordinates() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        testSubjectBlack.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubjectBlack.getXCoordinate());
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Move_IllegalCoordinates_Backwards_UpdatesCoordinates() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE, true);
        testSubjectWhite.move(MovementType.MOVE, 0, 3);
        assertEquals(1, testSubjectWhite.getXCoordinate());
        assertEquals(3, testSubjectWhite.getYCoordinate());
    }

    @Test
    public void testBlackPawn_Move_IllegalCoordinates_Forward_Distance_Too_Big() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK, true);
        testSubjectBlack.move(MovementType.MOVE, 2, 3);
        assertEquals(6, testSubjectBlack.getXCoordinate());
        assertEquals(3, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testWhitePawn_Move_IllegalCoordinates_Forward_Distance_Too_Big() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE, true);
        testSubjectWhite.move(MovementType.MOVE, 5, 3);
        assertEquals(1, testSubjectWhite.getXCoordinate());
        assertEquals(3, testSubjectWhite.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_LegalCoordinates() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE, true);
        chessBoard.addPiece(testSubjectBlack, 2, 4, PieceColor.BLACK, false);
        testSubjectWhite.move(MovementType.CAPTURE, 2, 4);
        assertEquals(2, testSubjectWhite.getXCoordinate());
        assertEquals(4, testSubjectWhite.getYCoordinate());
        assertEquals(-1, testSubjectBlack.getXCoordinate());
        assertEquals(-1, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_IllegalCoordinates() {
        chessBoard.addPiece(testSubjectWhite, 2, 4, PieceColor.WHITE, false);
        chessBoard.addPiece(testSubjectBlack, 1, 5, PieceColor.BLACK, false);
        testSubjectWhite.move(MovementType.CAPTURE, 1, 5);
        assertEquals(2, testSubjectWhite.getXCoordinate());
        assertEquals(4, testSubjectWhite.getYCoordinate());
        assertEquals(1, testSubjectBlack.getXCoordinate());
        assertEquals(5, testSubjectBlack.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_IllegalSameColors() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE, true);
        chessBoard.addPiece(testSubjectWhiteAux, 2, 4, PieceColor.WHITE, false);
        testSubjectWhite.move(MovementType.CAPTURE, 2, 4);
        assertEquals(1, testSubjectWhite.getXCoordinate());
        assertEquals(3, testSubjectWhite.getYCoordinate());
        assertEquals(2, testSubjectWhiteAux.getXCoordinate());
        assertEquals(4, testSubjectWhiteAux.getYCoordinate());
    }

}