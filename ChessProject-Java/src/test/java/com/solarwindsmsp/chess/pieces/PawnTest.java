package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.*;
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
    public void testChessBoard_Add_Sets_XCoordinate() throws IllegalBoardPlacementException {
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() throws IllegalBoardPlacementException {
        this.chessBoard.add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() throws IllegalBoardPlacementException {
        chessBoard.add(testSubject, 6, 3);
        try {
            testSubject.move(MovementType.MOVE, 7, 3);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() throws IllegalBoardPlacementException {
        chessBoard.add(testSubject, 6, 3);
        try {
            testSubject.move(MovementType.MOVE, 4, 3);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() throws IllegalBoardPlacementException, IllegalMoveException {
        chessBoard.add(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

}