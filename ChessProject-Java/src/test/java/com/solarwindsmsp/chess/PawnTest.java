package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import org.junit.After;
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
    }

    @After
    public void TearDown() {
        this.testSubject.removeFromBoard();
    }

    @Test
    public void testPawn_toString_validateOutput() {
        testSubject.setColCoordinate(5);
        testSubject.setRowCoordinate(5);
        String output = testSubject.toString();
        assertEquals(output, "Algebraic Notation: F6\nCurrent Column: 5\nCurrent Row: 5\nPiece Color: BLACK");
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getRowCoordinate());
    }

    @Test
    public void testChessBoard_Add_Pawn_Invalid_Location() {
        this.chessBoard.addPiece(testSubject, 9, 9);
        assertEquals(-1, testSubject.getRowCoordinate());
        assertEquals(-1, testSubject.getColCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getColCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getRowCoordinate());
        assertEquals(3, testSubject.getColCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getRowCoordinate());
        assertEquals(3, testSubject.getColCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Black_Forward_UpdatesCoordinates() {
        /* Pawns move forward. Their ROW changes from 3 to 2 as a BLACK pawn moves forward */
        chessBoard.addPiece(testSubject, 3, 6);
        testSubject.move(MovementType.MOVE, 2, 6);
        assertEquals(6, testSubject.getColCoordinate());
        assertEquals(2, testSubject.getRowCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_White_Backward_UpdatesCoordinates() {
        Pawn whitePiece = new Pawn(PieceColor.WHITE);
        chessBoard.addPiece(whitePiece, 3, 6);
        whitePiece.move(MovementType.MOVE, 4, 6);
        assertEquals(6, whitePiece.getColCoordinate());
        assertEquals(4, whitePiece.getRowCoordinate());
    }
}