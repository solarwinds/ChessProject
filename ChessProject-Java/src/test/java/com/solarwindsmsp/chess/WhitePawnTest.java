package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhitePawnTest {

    private ChessBoard chessBoard;
    private Pawn pawn;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
        pawn = new Pawn(PieceColor.WHITE, chessBoard);
    }

    @Test
    public void testChessBoard_Add_Sets_Coordinates() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 7, 3);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Down_DoesNotMove() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 6, 2);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 4, 3);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 6, 4);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(4, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_2Spaces_Forward() {
        chessBoard.addPiece(pawn, 6, 1, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 6, 3);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_Left() {
        chessBoard.addPiece(pawn, 1, 1, pawn.getPieceColor());

        Pawn blackPawn = new Pawn(PieceColor.BLACK, chessBoard);
        chessBoard.addPiece(blackPawn, 0, 2, blackPawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 0, 2);
        assertEquals(0, pawn.getXCoordinate());
        assertEquals(2, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_Right() {
        chessBoard.addPiece(pawn, 1, 1, pawn.getPieceColor());

        Pawn blackPawn = new Pawn(PieceColor.BLACK, chessBoard);
        chessBoard.addPiece(blackPawn, 2, 2, blackPawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 2, 2);
        assertEquals(2, pawn.getXCoordinate());
        assertEquals(2, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Cant_Capture_If_No_Piece() {
        chessBoard.addPiece(pawn, 1, 1, pawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 2, 2);
        assertEquals(1, pawn.getXCoordinate());
        assertEquals(1, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Cant_Capture_Same_Color() {
        chessBoard.addPiece(pawn, 1, 1, pawn.getPieceColor());

        Pawn whitePawn = new Pawn(PieceColor.WHITE, chessBoard);
        chessBoard.addPiece(whitePawn, 2, 2, whitePawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 2, 2);
        assertEquals(1, pawn.getXCoordinate());
        assertEquals(1, pawn.getYCoordinate());
    }
}