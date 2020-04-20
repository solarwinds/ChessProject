package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.exception.IllegalMoveException;
import com.solarwindsmsp.chess.movement.MovementType;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(chessBoard, PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test(expected = IllegalMoveException.class)
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test(expected = IllegalMoveException.class)
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test(expected = IllegalMoveException.class)
    public void testPawn_shouldNotMoveBackwards() {
        chessBoard.addPiece(testSubject, 4,4);
        testSubject.move(MovementType.MOVE, 3, 4);
    }


    @Test(expected = IllegalMoveException.class)
    public void testPawn_CannotMoveIfBlocked() {
        Pawn firstPawn = new Pawn(chessBoard, PieceColor.WHITE);
        Pawn secondPawn = new Pawn(chessBoard, PieceColor.WHITE);

        chessBoard.addPiece(firstPawn, 6, 3);
        chessBoard.addPiece(secondPawn, 6, 4);

        assertEquals(0, firstPawn.getPossibleMoves().size());
        assertEquals(1, secondPawn.getPossibleMoves().size());

        firstPawn.move(MovementType.MOVE, 6,4);
    }

    @Test
    public void testPawn_capture() {
        Pawn firstPawn = new Pawn(chessBoard, PieceColor.WHITE);
        Pawn secondPawn = new Pawn(chessBoard, PieceColor.BLACK);

        chessBoard.addPiece(firstPawn, 4, 4);
        chessBoard.addPiece(secondPawn, 5, 5);

        assertEquals(2, firstPawn.getPossibleMoves().size());
        firstPawn.move(MovementType.CAPTURE, 5,5);

        assertEquals(5, firstPawn.getPosition().getX());
        assertEquals(5, firstPawn.getPosition().getY());

        assertNull(chessBoard.getPosition(4,4).getPiece());

        assertEquals(1, chessBoard.getBoardInventory().getOnBoard().size());
        assertEquals(1, chessBoard.getBoardInventory().getCaptured().size());
    }

    @Test(expected = IllegalMoveException.class)
    public void testPawn_invalid_capture() {
        Pawn firstPawn = new Pawn(chessBoard, PieceColor.WHITE);
        Pawn secondPawn = new Pawn(chessBoard, PieceColor.BLACK);

        chessBoard.addPiece(firstPawn, 4, 4);
        chessBoard.addPiece(secondPawn, 5, 4);

        firstPawn.move(MovementType.CAPTURE, 5,4);

    }
}