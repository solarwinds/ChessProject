package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PawnTest {

    public static final String CAPTURE_CONDITIONS_NOT_MET = "Capture conditions not met";
    public static final int X_COORDINATE_7 = 7;
    public static final int X_COORDINATE_6 = 6;
    public static final int Y_COORDINATE_3 = 3;
    public static final int Y_COORDINATE_2 = 2;
    public static final int Y_COORDINATE_4 = 4;
    private ChessBoard chessBoard;
    private Piece piece;

    @Before
    public void setUp() {
        this.chessBoard = ChessBoard.getInstance();
        this.chessBoard.createBoard();
        PieceFactory factory = PieceFactory.getInstance();
        this.piece = factory.createPiece(Piece.TypePiece.PAWN, PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        assertEquals(X_COORDINATE_6, piece.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        assertEquals(Y_COORDINATE_3, piece.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        piece.move(MovementType.MOVE, X_COORDINATE_7, Y_COORDINATE_3);
        assertEquals(X_COORDINATE_6, piece.getXCoordinate());
        assertEquals(Y_COORDINATE_3, piece.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        piece.move(MovementType.MOVE, Y_COORDINATE_4, Y_COORDINATE_3);
        assertEquals(X_COORDINATE_6, piece.getXCoordinate());
        assertEquals(Y_COORDINATE_3, piece.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        piece.move(MovementType.MOVE, X_COORDINATE_6, Y_COORDINATE_2);
        assertEquals(X_COORDINATE_6, piece.getXCoordinate());
        assertEquals(Y_COORDINATE_2, piece.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_CaptureLeft_UpdatesCoordinates_Success() {
        chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        piece.move(MovementType.CAPTURE, 5, Y_COORDINATE_4);
        assertEquals(5, piece.getXCoordinate());
        assertEquals(Y_COORDINATE_4, piece.getYCoordinate());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPawn_Move_LegalCoordinates_CaptureLeft_UpdatesCoordinates_Fail() {
        try {
            chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
            piece.move(MovementType.CAPTURE, X_COORDINATE_7, Y_COORDINATE_2);
        } catch (UnsupportedOperationException e) {
            assertEquals(CAPTURE_CONDITIONS_NOT_MET, e.getMessage());
            throw e;
        }
    }

    @Test
    public void testPawn_Move_LegalCoordinates_CaptureRight_UpdatesCoordinates_Success() {
        chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
        piece.move(MovementType.CAPTURE, X_COORDINATE_7, Y_COORDINATE_4);
        assertEquals(X_COORDINATE_7, piece.getXCoordinate());
        assertEquals(Y_COORDINATE_4, piece.getYCoordinate());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPawn_Move_LegalCoordinates_CaptureRight_UpdatesCoordinates_Fail() {
        try {
            chessBoard.addPiece(piece, X_COORDINATE_6, Y_COORDINATE_3);
            piece.move(MovementType.CAPTURE, X_COORDINATE_7, Y_COORDINATE_2);
        } catch (UnsupportedOperationException e) {
            assertEquals(CAPTURE_CONDITIONS_NOT_MET, e.getMessage());
            throw e;
        }
    }

}