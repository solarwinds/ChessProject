package com.solarwindsmsp.chess;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() throws IllegalBoardCoordinatesException {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() throws IllegalBoardCoordinatesException {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test(expected = IllegalBoardCoordinatesException.class)
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() throws IllegalBoardCoordinatesException, InvalidArgumentException {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test(expected = IllegalBoardCoordinatesException.class)
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() throws IllegalBoardCoordinatesException, InvalidArgumentException {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() throws IllegalBoardCoordinatesException, InvalidArgumentException {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(2, testSubject.getYCoordinate());
    }

}