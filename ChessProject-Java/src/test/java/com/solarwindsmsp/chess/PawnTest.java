package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn blackTestSubject;
    private Pawn whiteTestSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.blackTestSubject = new Pawn(PieceColor.BLACK);
        this.whiteTestSubject = new Pawn(PieceColor.WHITE);
    }

    @Test
    public void testChessBoard_Add_Sets_Coordinates() {
        this.chessBoard.add(blackTestSubject, 6, 3);
        assertEquals(6, blackTestSubject.getXCoordinate());
        assertEquals(3, blackTestSubject.getYCoordinate());
    }

    @Test
    public void blackTestPawn_Move_IllegalCoordinates_Backward_DoesNotMove() {
        chessBoard.add(blackTestSubject, 6, 3);
        blackTestSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, blackTestSubject.getXCoordinate());
        assertEquals(3, blackTestSubject.getYCoordinate());
    }

    @Test
    public void blackTestPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(blackTestSubject, 6, 2);
        blackTestSubject.move(MovementType.MOVE, 5, 2);
        assertEquals(5, blackTestSubject.getXCoordinate());
        assertEquals(2, blackTestSubject.getYCoordinate());
    }

    @Test
    public void whiteTestPawn_Move_IllegalCoordinates_Backward_DoesNotMove() {
        chessBoard.add(whiteTestSubject, 1, 3);
        whiteTestSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(1, whiteTestSubject.getXCoordinate());
        assertEquals(3, whiteTestSubject.getYCoordinate());
    }

    @Test
    public void whiteTestPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.add(whiteTestSubject, 1, 4);
        whiteTestSubject.move(MovementType.MOVE, 2, 4);
        assertEquals(2, whiteTestSubject.getXCoordinate());
        assertEquals(4, whiteTestSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_TwoSpaces_DoesNotMove() {
        chessBoard.add(blackTestSubject, 6, 3);
        blackTestSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, blackTestSubject.getXCoordinate());
        assertEquals(3, blackTestSubject.getYCoordinate());
    }

    @Test
    public void testPawn_ToString_ReturnsTextInformation(){
        chessBoard.add(blackTestSubject, 2, 3);
        String output = blackTestSubject.toString();
        String eol = System.lineSeparator();
        assertEquals("Current X: 2"+eol+"Current Y: 3"+eol+"Piece Color: BLACK",output);
    }

    @Test
    public void testPawn_Move_IllegalMove_OnExistingPawn_DoesNotMove(){
        chessBoard.add(blackTestSubject,5,4);
        chessBoard.add(whiteTestSubject,4,4);
        blackTestSubject.move(MovementType.MOVE,4,4);
        assertEquals(5,blackTestSubject.getXCoordinate());
        assertEquals(4,blackTestSubject.getYCoordinate());
    }
}