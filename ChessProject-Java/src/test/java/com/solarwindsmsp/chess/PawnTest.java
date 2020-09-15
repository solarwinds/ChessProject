package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
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

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubject.getRowCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getColCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getRowCoordinate());
        assertEquals(3, testSubject.getColCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getRowCoordinate());
        assertEquals(3, testSubject.getColCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        /* Pawns move forward. Their ROW changes from 3 to 2 as a BLACK pawn moves forward */
        chessBoard.addPiece(testSubject, 3, 6, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 2, 6);
        assertEquals(6, testSubject.getColCoordinate());
        assertEquals(2, testSubject.getRowCoordinate());
    }

}