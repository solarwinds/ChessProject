package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.game.ChessBoard;
import com.solarwindsmsp.chess.game.MovementType;
import com.solarwindsmsp.chess.game.PieceColor;
import com.solarwindsmsp.chess.game.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @BeforeEach
    public void setup() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testPieceType_shouldReturnPawn() {
        Pawn pawn = new Pawn(PieceColor.WHITE);

        assertEquals(PieceType.PAWN, pawn.getPieceType());
    }

    @Test
    public void addPiece_shouldSetXCoordinate() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void addPiece_shouldSeYXCoordinate() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetInvalidCoordinates_whenInvalidStartingPoint_BlackPawn() {
        chessBoard.addPiece(testSubject, 7, 3, PieceColor.BLACK);

        assertEquals(-1, testSubject.getXCoordinate());
        assertEquals(-1, testSubject.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetValidCoordinates_whenValidStartingPoint_BlackPawn() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetInvalidCoordinates_whenInvalidStartingPoint_WhitePawn() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 0, 3, PieceColor.WHITE);

        assertEquals(-1, whitePawn.getXCoordinate());
        assertEquals(-1, whitePawn.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetValidCoordinates_whenValidStartingPoint_WhitePawn() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);

        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
    }

    @Test
    public void move_shouldKeepCoordinates_whenIllegalMoveToTheRight_BlackPawn() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 4);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void move_shouldKeepCoordinates_whenIllegalMoveToTheLeft_BlackPawn() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 2);

        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void move_shouldUpdateCoordinates_whenLegalMoveForward_BlackPawn() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 5, 3);

        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
        assertEquals(chessBoard, testSubject.getChessBoard());
    }

    @Test
    public void move_shouldUpdateCoordinates_whenLegalMoveForward_WhitePawn() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 2, 3);

        assertEquals(2, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
        assertEquals(chessBoard, whitePawn.getChessBoard());
    }

    @Test
    public void move_shouldKeepCoordinates_whenIllegalMoveForward2Spaces_BlackPawn() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 3, 3);

        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
    }

    @Test
    public void move_shouldKeepCoordinates_whenIllegalMoveBackward_BlackPawn() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);

        chessBoard.addPiece(whitePawn, 1, 3, PieceColor.WHITE);
        whitePawn.move(MovementType.MOVE, 0, 3);

        assertEquals(1, whitePawn.getXCoordinate());
        assertEquals(3, whitePawn.getYCoordinate());
    }
}