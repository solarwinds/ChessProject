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

//    @Test
//    public void test_Pawn_Move_IllegalCoordinates_Right_Has_No_Effect() {
//        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
//        testSubject.move(MovementType.MOVE, 7, 3);
//
//        TestHelper.assertCoordinates(testSubject, 6, 3);
//    }
//
//    @Test
//    public void test_Pawn_Move_IllegalCoordinates_Left_Has_No_Effect() {
//        chessBoard.add(testSubject, 6, 3, PieceColor.BLACK);
//        testSubject.move(MovementType.MOVE, 4, 3);
//
//        TestHelper.assertCoordinates(testSubject, 6, 3);
//    }
//
//    @Test
//    public void test_Black_Pawn_Move_LegalCoordinates_Forward_Updates_Coordinates_And_Moves() {
//        chessBoard.add(testSubject, 6, 6, PieceColor.BLACK);
//        testSubject.move(MovementType.MOVE, 6, 5);
//
//        TestHelper.assertCoordinates(testSubject, 6, 5);
//
//        assertNull(chessBoard.getPieces()[6][6]);
//        assertEquals("p", chessBoard.getPieces()[6][5].toString());
//    }
//
//    @Test
//    public void test_White_Pawn_Move_LegalCoordinates_Forward_Updates_Coordinates_And_Moves() {
//        Piece whitePawn = new Pawn(PieceColor.WHITE);
//        whitePawn.setChessBoard(chessBoard);
//        chessBoard.add(whitePawn, 6, 1, PieceColor.WHITE);
//
//        System.out.println(chessBoard);
//
//        whitePawn.move(MovementType.MOVE, 6, 2);
//
//        TestHelper.assertCoordinates(whitePawn, 6, 2);
//
//        assertNull(chessBoard.getPieces()[6][1]);
//        assertEquals("P", chessBoard.getPieces()[6][2].toString());
//
//        System.out.println(chessBoard);
//    }
//
//    @Test
//    public void test_Black_Pawn_Move_IllegalTargetCoordinates_Forward_Has_No_Effect() {
//        chessBoard.add(testSubject, 6, 0, PieceColor.BLACK);
//        testSubject.move(MovementType.MOVE, 6, -1);
//
//        TestHelper.assertCoordinates(testSubject, 6, 0);
//    }
//
//    @Test
//    public void test_White_Pawn_Move_IllegalTargetCoordinates_Forward_Has_No_Effect() {
//        Piece whitePawn = new Pawn(PieceColor.WHITE);
//        whitePawn.setChessBoard(chessBoard);
//        chessBoard.add(whitePawn, 6, 7, PieceColor.WHITE);
//        whitePawn.move(MovementType.MOVE, 6, 8);
//
//        TestHelper.assertCoordinates(whitePawn, 6, 7);
//    }

}