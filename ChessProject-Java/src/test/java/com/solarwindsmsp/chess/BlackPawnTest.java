package com.solarwindsmsp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackPawnTest {

    private ChessBoard chessBoard;
    private Pawn pawn;

    @BeforeEach
    public void setUp() {
        chessBoard = new ChessBoard();
        pawn = new Pawn(PieceColor.BLACK, chessBoard);
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
    public void testPawn_Move_IllegalCoordinates_Up_DoesNotMove() {
        chessBoard.addPiece(pawn, 6, 3, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 6, 4);
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
        pawn.move(MovementType.MOVE, 6, 2);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(2, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_2Spaces_Forward() {
        chessBoard.addPiece(pawn, 6, 6, pawn.getPieceColor());
        pawn.move(MovementType.MOVE, 6, 4);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(4, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_Left() {
        chessBoard.addPiece(pawn, 6, 6, pawn.getPieceColor());

        Pawn whitePawn = new Pawn(PieceColor.WHITE, chessBoard);
        chessBoard.addPiece(whitePawn, 5, 5, whitePawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 5, 5);
        assertEquals(5, pawn.getXCoordinate());
        assertEquals(5, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Capture_Right() {
        chessBoard.addPiece(pawn, 6, 6, pawn.getPieceColor());

        Pawn whitePawn = new Pawn(PieceColor.WHITE, chessBoard);
        chessBoard.addPiece(whitePawn, 7, 5, whitePawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 7, 5);
        assertEquals(7, pawn.getXCoordinate());
        assertEquals(5, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Cant_Capture_If_No_Piece() {
        chessBoard.addPiece(pawn, 6, 6, pawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 7, 5);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(6, pawn.getYCoordinate());
    }

    @Test
    public void testPawn_Cant_Capture_Same_Color() {
        chessBoard.addPiece(pawn, 6, 6, pawn.getPieceColor());

        Pawn blackPawn = new Pawn(PieceColor.BLACK, chessBoard);
        chessBoard.addPiece(blackPawn, 7, 5, blackPawn.getPieceColor());

        pawn.move(MovementType.CAPTURE, 7, 5);
        assertEquals(6, pawn.getXCoordinate());
        assertEquals(6, pawn.getYCoordinate());
    }
}