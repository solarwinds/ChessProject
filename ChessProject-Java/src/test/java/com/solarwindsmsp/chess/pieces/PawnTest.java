package com.solarwindsmsp.chess.pieces;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.exceptions.InvalidMoveException;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testPiece_getMaxNumberOfPieces() {
        assertEquals(8, testSubject.getMaxNumberOfPieces());
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_isValidMoveForPiece_oneRowForward_valid() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertTrue(this.testSubject.isValidMoveForPiece(MovementType.MOVE, 6, 2));
    }

    @Test
    public void testPawn_isValidMoveForPiece_oneRowBackward_invalid() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertFalse(this.testSubject.isValidMoveForPiece(MovementType.MOVE, 6, 4));
    }

    @Test
    public void testPawn_isValidMoveForPiece_oneColumnToRight_invalid() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertFalse(this.testSubject.isValidMoveForPiece(MovementType.MOVE, 7, 3));
    }

    @Test
    public void testPawn_isValidMoveForPiece_twoRowsForward_valid() throws Exception {
        this.chessBoard.addPiece(testSubject, 6, 6);
        assertTrue(this.testSubject.isValidMoveForPiece(MovementType.MOVE, 6, 4));
    }

    @Test
    public void testPawn_isValidMoveForPiece_twoRowsBackward_invalid() throws Exception {
        this.chessBoard.addPiece(testSubject, 5, 3);
        assertFalse(this.testSubject.isValidMoveForPiece(MovementType.MOVE, 5, 1));
    }

    @Test
    public void testPawn_isValidMoveForChessBoard_valid() throws Exception {
        chessBoard.addPiece(testSubject, 6, 3);
        assertTrue(testSubject.isValidMoveForChessBoard(MovementType.MOVE, 6, 2));
    }

    /*
     * White pawn is blocking black pawn movement
     */
    @Test
    public void testPawn_isValidMoveForChessBoard_invalid() throws Exception {
        chessBoard.addPiece(testSubject, 6, 6);
        chessBoard.addPiece(new Pawn(PieceColor.WHITE), 6, 5);

        assertFalse(testSubject.isValidMoveForChessBoard(MovementType.MOVE, 6, 4));
    }

    @Test(expected = InvalidMoveException.class)
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() throws Exception {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test(expected = InvalidMoveException.class)
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() throws Exception {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() throws Exception {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void test_assignPieceToChessBoard() {
        testSubject.assignPieceToChessBoard(chessBoard, 4, 3);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
}