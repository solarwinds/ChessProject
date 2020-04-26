package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.model.ChessBoard;
import com.solarwindsmsp.chess.model.ChessPiece;
import com.solarwindsmsp.chess.model.enums.PieceColor;
import com.solarwindsmsp.chess.model.enums.PieceType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private ChessPiece testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new ChessPiece(PieceColor.WHITE, PieceType.PAWN);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getxCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getyCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        chessBoard.movePiece(testSubject,7, 3);
        assertEquals(6, testSubject.getxCoordinate());
        assertEquals(3, testSubject.getyCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        chessBoard.movePiece(testSubject, 4, 3);
        assertEquals(6, testSubject.getxCoordinate());
        assertEquals(3, testSubject.getyCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3);
        chessBoard.movePiece(testSubject, 6, 2);
        assertEquals(6, testSubject.getxCoordinate());
        assertEquals(2, testSubject.getyCoordinate());
    }

}