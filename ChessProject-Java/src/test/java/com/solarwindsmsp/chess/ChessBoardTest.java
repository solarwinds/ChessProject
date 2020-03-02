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
                assertTrue(testSubject.IsLegalBoardPosition(rank, file));
            }
        }
    }

    @Test
    public void test_Assorted_Illegal_Board_Positions() {
        assertFalse(testSubject.IsLegalBoardPosition(11, 5));
        assertFalse(testSubject.IsLegalBoardPosition(0, 9));
        assertFalse(testSubject.IsLegalBoardPosition(11, 0));
        assertFalse(testSubject.IsLegalBoardPosition(5, -1));
        assertFalse(testSubject.IsLegalBoardPosition(-1, 5));
    }

    @Test
    public void test_Avoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.Add(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void test_Limits_The_Number_Of_Black_Coloured_Pawns() {

        AddFullRankOfPawns(PieceColor.BLACK, 6);
        addPawn(PieceColor.BLACK, 5, 0);

        assertThatPiecesArePresentAtExpectedCoordinates(6, ChessBoard.WIDTH);
        assertNull(testSubject.getPieces()[5][0]);
    }

    @Test
    public void test_Limits_The_Number_Of_White_Coloured_Pawns() {

        AddFullRankOfPawns(PieceColor.WHITE, 1);
        addPawn(PieceColor.WHITE, 2, 0);

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
            addPawn(colour, rank, file);
        }
    }

    private void addPawn(PieceColor colour, int rank, int file) {
        testSubject.Add(new Pawn(colour), rank, file, colour);
    }

}