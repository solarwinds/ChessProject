package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubjectBlack;
    private Pawn testSubjectWhite;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubjectBlack = new Pawn(PieceColor.BLACK);
        this.testSubjectWhite = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate_Black() {
        this.chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubjectBlack.getxCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate_Black() {
        this.chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubjectBlack.getyCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate_White() {
        this.chessBoard.addPiece(testSubjectBlack, 1, 3, PieceColor.WHITE);
        assertEquals(1, testSubjectBlack.getxCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate_White() {
        this.chessBoard.addPiece(testSubjectBlack, 1, 3, PieceColor.WHITE);
        assertEquals(3, testSubjectBlack.getyCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        testSubjectBlack.move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubjectBlack.getxCoordinate());
        assertEquals(3, testSubjectBlack.getyCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        testSubjectBlack.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubjectBlack.getxCoordinate());
        assertEquals(3, testSubjectBlack.getyCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_Black() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        testSubjectBlack.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubjectBlack.getxCoordinate());
        assertEquals(3, testSubjectBlack.getyCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_White() {
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE);
        testSubjectWhite.move(MovementType.MOVE, 1, 4);
        assertEquals(1, testSubjectWhite.getxCoordinate());
        assertEquals(3, testSubjectWhite.getyCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        chessBoard.addPiece(testSubjectWhite, 1, 3, PieceColor.WHITE);
        testSubjectWhite.move(MovementType.MOVE, 2, 3);
        testSubjectBlack.move(MovementType.MOVE, 5, 3);
        testSubjectWhite.move(MovementType.MOVE, 3, 3);
        testSubjectBlack.move(MovementType.MOVE, 4, 3);
        testSubjectWhite.move(MovementType.MOVE, 4,3);

        assertEquals(3, testSubjectWhite.getxCoordinate());
    }

    @Test
    public void testPawn_Capture_LegalCoordinates() {
        chessBoard.addPiece(testSubjectBlack, 6, 3, PieceColor.BLACK);
        chessBoard.addPiece(testSubjectWhite, 1, 2, PieceColor.WHITE);
        testSubjectWhite.move(MovementType.MOVE, 2, 2);
        testSubjectBlack.move(MovementType.MOVE, 5, 3);
        testSubjectWhite.move(MovementType.MOVE, 3, 2);
        testSubjectBlack.move(MovementType.MOVE, 4, 3);

        testSubjectWhite.capture(MovementType.CAPTURE, 4,3);

        assertEquals(4, testSubjectWhite.getxCoordinate());
    }

}