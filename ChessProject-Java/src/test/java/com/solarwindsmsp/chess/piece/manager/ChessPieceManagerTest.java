package com.solarwindsmsp.chess.piece.manager;

import com.solarwindsmsp.chess.chessboard.ChessBoard;
import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.piece.manager.ChessPieceManager;
import com.solarwindsmsp.chess.piece.manager.MovementType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

public class ChessPieceManagerTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test_PerformAction_Capture_Throws_Unsupported_Operation_Exception() {
        ChessPiece pawn = new Pawn(PieceColor.BLACK);

        ChessPieceManager.performAction(pawn, MovementType.CAPTURE, 1, 2);
    }

    @Test
    public void test_PerformAction_Move_Invalid_Move_For_Piece() {
        ChessPiece pawn = new Pawn(PieceColor.BLACK);
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.add(pawn, 0, 1);

        boolean actionResult = ChessPieceManager.performAction(pawn, MovementType.MOVE, 0, 7);

        assertFalse(actionResult);
        assertEquals(pawn.getXCoordinate(), 0);
        assertEquals(pawn.getYCoordinate(), 1);
    }

    @Test
    public void test_PerformAction_Move_Valid_Move_For_Piece() {
        ChessPiece pawn = new Pawn(PieceColor.BLACK);
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.add(pawn, 0, 1);

        boolean actionResult = ChessPieceManager.performAction(pawn, MovementType.MOVE, 0, 0);

        assertTrue(actionResult);
        assertEquals(pawn.getXCoordinate(), 0);
        assertEquals(pawn.getYCoordinate(), 0);
        assertFalse(chessBoard.getBoard()[0][1].getChessPiece().isPresent());
        assertTrue(chessBoard.getBoard()[0][0].getChessPiece().isPresent());
    }

    @Test
    public void test_PerformAction_Move_Valid_Move_For_Piece_Invalid_Move_On_Chessboard() {
        ChessPiece firstPawn = new Pawn(PieceColor.BLACK);
        ChessPiece secondPawn = new Pawn(PieceColor.BLACK);
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.add(firstPawn, 0, 1);
        chessBoard.add(secondPawn, 0, 0);

        boolean actionResult = ChessPieceManager.performAction(firstPawn, MovementType.MOVE, 0, 0);

        assertFalse(actionResult);

        assertTrue(chessBoard.getBoard()[0][1].getChessPiece().isPresent());
        assertSame(chessBoard.getBoard()[0][1].getChessPiece().get(), firstPawn);
        assertEquals(firstPawn.getXCoordinate(), 0);
        assertEquals(firstPawn.getYCoordinate(), 1);

        assertTrue(chessBoard.getBoard()[0][0].getChessPiece().isPresent());
        assertSame(chessBoard.getBoard()[0][0].getChessPiece().get(), secondPawn);
        assertEquals(secondPawn.getXCoordinate(), 0);
        assertEquals(secondPawn.getYCoordinate(), 0);
    }
}