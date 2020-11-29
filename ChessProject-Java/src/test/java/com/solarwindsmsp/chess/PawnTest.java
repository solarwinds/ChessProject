package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.models.Piece;
import com.solarwindsmsp.chess.models.PieceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.solarwindsmsp.chess.models.Piece.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private Piece testSubject;

    @Before
    public void setUp() {
        this.chessBoard = ChessBoard.getInstance();
        this.chessBoard.clearBoard();
        this.testSubject = PieceFactory.createPiece(Type.Pawn, PieceColor.BLACK);
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

    @Test
    public void testPawn_MoveAction_Move_NegativeCoordinates_DoesNotMove() {
        chessBoard.addPiece(testSubject, 0, 0);
        testSubject.move(MovementType.MOVE, 0, -1);
        assertEquals(0, testSubject.getXCoordinate());
        assertEquals(0, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_IllegalCoordinates_RightForward_DoesNotMove() {
        chessBoard.addPiece(testSubject, 5, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_IllegalCoordinates_LeftForward_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 5, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_IllegalDirection_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Capture_NegativeCoordinates_DoesNotMove() {
        chessBoard.addPiece(testSubject, 0, 0);
        testSubject.move(MovementType.CAPTURE, 0, -1);
        assertEquals(0, testSubject.getXCoordinate());
        assertEquals(0, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Capture_IllegalDirection_LeftBackward_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.CAPTURE, 5, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Capture_IllegalDirection_RightBackward_DoesNotMove() {
        chessBoard.addPiece(testSubject, 5, 3);
        testSubject.move(MovementType.CAPTURE, 6, 4);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Capture_LegalCoordinates_LeftForward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.CAPTURE, 5, 2);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_MoveAction_Capture_LegalCoordinates_RightForward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 5, 3);
        testSubject.move(MovementType.CAPTURE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

}
