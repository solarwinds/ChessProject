package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KingTest {

    private ChessBoard chessBoard;
    private Piece testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new King(PieceColor.BLACK);
        this.testSubject.setChessBoard(chessBoard);
    }

    @Test
    public void test_ChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void test_ChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);

        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void test_Move_Illegal_Right_Has_No_Effect() {
        chessBoard.add(testSubject, 5, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 7, 3);

        TestHelper.assertCoordinates(testSubject, 5, 3);
    }

    @Test
    public void test_Move_Illegal_Left_Has_No_Effect() {
        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 4, 3);

        TestHelper.assertCoordinates(testSubject, 6, 3);
    }

    @Test
    public void test_Move_Legal_Forward_Updates_Coordinates_And_Moves() {
        chessBoard.add(testSubject, 6, 6, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 5);

        TestHelper.assertCoordinates(testSubject, 6, 5);

        assertNull(chessBoard.getPieces()[6][6]);
        assertEquals("k", chessBoard.getPieces()[6][5].toString());
    }

    @Test
    public void test_Move_Legal_Backward_Updates_Coordinates_And_Moves() {
        chessBoard.add(testSubject, 6, 5, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, 6);

        TestHelper.assertCoordinates(testSubject, 6, 6);

        assertNull(chessBoard.getPieces()[6][5]);
        assertEquals("k", chessBoard.getPieces()[6][6].toString());
    }

    @Test
    public void test_Move_Legal_Diagonal_Updates_Coordinates_And_Moves() {
        chessBoard.add(testSubject, 6, 5, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 7, 6);

        TestHelper.assertCoordinates(testSubject, 7, 6);

        assertNull(chessBoard.getPieces()[6][5]);
        assertEquals("k", chessBoard.getPieces()[7][6].toString());
    }

    @Test
    public void test_Move_Illegal_Coordinates_Forward_Has_No_Effect() {
        chessBoard.add(testSubject, 6, 0, PieceColor.BLACK);
        testSubject.move(MovementType.MOVE, 6, -1);

        TestHelper.assertCoordinates(testSubject, 6, 0);
    }

    @Test
    public void test_Move_Legal_Forward_To_Occupied_Square_Has_No_Effect() {
        chessBoard.add(testSubject, 6, 6, PieceColor.BLACK);
        chessBoard.add(new Pawn(PieceColor.WHITE), 6, 5, PieceColor.WHITE);

        testSubject.move(MovementType.MOVE, 6, 5);

        TestHelper.assertCoordinates(testSubject, 6, 6);

        assertEquals("k", chessBoard.getPieces()[6][6].toString());
        assertEquals("P", chessBoard.getPieces()[6][5].toString());
    }

}