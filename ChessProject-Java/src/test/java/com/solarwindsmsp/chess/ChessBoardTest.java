package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void test_Chessboard_Has_Width_of_8() {
        assertEquals(8, ChessBoard.HEIGHT);
    }

    @Test
    public void test_Chessboard_Has_Height_of_8() {
        assertEquals(8, ChessBoard.HEIGHT);
    }

    @Test
    public void test_All_Legal_Board_Positions() {
        for (int rank = 0; rank < ChessBoard.HEIGHT; rank++) {
            for (int file = 0; file < ChessBoard.WIDTH; file++) {
                assertTrue(testSubject.isLegalBoardPosition(rank, file));
            }
        }
    }

    @Test
    public void test_Assorted_Illegal_Board_Positions() {
        assertFalse(testSubject.isLegalBoardPosition(11, 5));
        assertFalse(testSubject.isLegalBoardPosition(0, 9));
        assertFalse(testSubject.isLegalBoardPosition(11, 0));
        assertFalse(testSubject.isLegalBoardPosition(5, -1));
        assertFalse(testSubject.isLegalBoardPosition(-1, 5));
    }

    @Test
    public void test_Avoids_Duplicate_Positioning() {
        Piece p1 = new Pawn(PieceColor.BLACK);
        Piece p2 = new Pawn(PieceColor.BLACK);

        testSubject.add(p1, 6, 3, PieceColor.BLACK);
        testSubject.add(p2, 6, 3, PieceColor.BLACK);

        assertCoordinates(p1, 6, 3);
        assertCoordinates(p2, -1, -1);
    }

    @Test
    public void test_Limits_The_Number_Of_Black_Coloured_Pawns() {

        AddFullRankOfPawns(PieceColor.BLACK, 6);
        addPiece(new Pawn(PieceColor.BLACK), 5, 0);

        assertThatPiecesArePresentAtExpectedCoordinates(6, ChessBoard.WIDTH);
        assertNull(testSubject.getPieces()[5][0]);
    }

    @Test
    public void test_Limits_The_Number_Of_White_Coloured_Pawns() {
        AddFullRankOfPawns(PieceColor.WHITE, 1);
        addPiece(new Pawn(PieceColor.WHITE), 2, 0);

        assertThatPiecesArePresentAtExpectedCoordinates(1, ChessBoard.WIDTH);
        assertNull(testSubject.getPieces()[2][0]);
    }

    @Test
    public void test_Allows_Addition_Of_Maximum_Number_Of_Both_Coloured_Pawns() {
        AddFullRankOfPawns(PieceColor.BLACK, 6);
        AddFullRankOfPawns(PieceColor.WHITE, 1);

        assertThatPiecesArePresentAtExpectedCoordinates(6, ChessBoard.WIDTH);
        assertThatPiecesArePresentAtExpectedCoordinates(1, ChessBoard.WIDTH);
    }

    // helper methods

    private void assertThatPiecesArePresentAtExpectedCoordinates(int rank, int upToFile) {
        for (int file = 0; file < upToFile; file++) {
            assertEquals(file, testSubject.getPieces()[rank][file].getYCoordinate());
            assertEquals(rank, testSubject.getPieces()[rank][file].getXCoordinate());
        }
    }

    private void AddFullRankOfPawns(PieceColor colour, int rank) {
        for (int file = 0; file < ChessBoard.WIDTH; file++) {
            addPiece(new Pawn(colour), rank, file);
        }
    }

    private void addPiece(Piece p, int rank, int file) {
        testSubject.add(p, rank, file, p.getPieceColor());
    }

    private void assertCoordinates(Piece p, int x, int y) {
        assertEquals(x, p.getXCoordinate());
        assertEquals(y, p.getYCoordinate());
    }

}