package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.chessboard.ChessBoard;
import com.solarwindsmsp.chess.piece.manager.ChessPieceManager;
import com.solarwindsmsp.chess.piece.manager.MovementType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

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
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3);
        boolean actionResult = ChessPieceManager.performAction(this.testSubject, MovementType.MOVE, 7, 3);
        assertFalse(actionResult);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.add(testSubject, 6, 3);
        boolean actionResult = ChessPieceManager.performAction(this.testSubject, MovementType.MOVE, 4, 3);
        assertFalse(actionResult);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(testSubject, 6, 3);
        boolean actionResult = ChessPieceManager.performAction(this.testSubject, MovementType.MOVE, 6, 2);
        assertTrue(actionResult);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Backward_Does_Not_Move() {
        chessBoard.add(testSubject, 6, 3);
        ChessPieceManager.performAction(this.testSubject, MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_ToString_Prints_Position() {
        chessBoard.add(testSubject, 6, 3);

        String toStringResult = testSubject.toString();

        assertEquals("Current X: 6\nCurrent Y: 3\nPiece Color: BLACK", toStringResult);
    }
}