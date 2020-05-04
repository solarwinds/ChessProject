package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.enums.MovementType;
import com.solarwindsmsp.chess.models.ChessBoard;
import com.solarwindsmsp.chess.models.ChessPiece;
import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.enums.PieceColor.BLACK;
import static com.solarwindsmsp.chess.enums.PieceType.PAWN;
import static org.junit.Assert.assertEquals;

public class PawnTest {

    private ChessBoard chessBoard;
    private ChessPiece testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new ChessPiece(PAWN, BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(6, testSubject.getCell().getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3);
        assertEquals(3, testSubject.getCell().getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getCell().getXCoordinate());
        assertEquals(3, testSubject.getCell().getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getCell().getXCoordinate());
        assertEquals(3, testSubject.getCell().getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(testSubject, 6, 3);
        testSubject.move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getCell().getXCoordinate());
        assertEquals(2, testSubject.getCell().getYCoordinate());
    }

}
