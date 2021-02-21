package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ChessBoardTest {

    // Class under test
    private ChessBoard testSubject;

    // Mocks used for test
    private Piece mockPawn;

    @Before
    public void setUp() {
        testSubject = new ChessBoard();
        mockPawn = mock(Pawn.class);
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, 5);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_X_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(-1, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, -1);
        assertFalse(isValidPosition);
    }

    @Test
    public void testAddPiece_True_For_Empty_Square() {
        when(mockPawn.getXCoordinate()).thenReturn(0);
        when(mockPawn.getYCoordinate()).thenReturn(0);
        boolean pieceAdded = testSubject.addPiece(mockPawn);
        assertTrue(pieceAdded);
    }

    @Test
    public void testAddPiece_False_For_Occupied_Square() {
        when(mockPawn.getXCoordinate()).thenReturn(0);
        when(mockPawn.getYCoordinate()).thenReturn(0);
        testSubject.addPiece(mockPawn);
        boolean pieceAdded = testSubject.addPiece(mockPawn);
        assertFalse(pieceAdded);
    }

    @Test
    public void testLimits_The_Number_Of_Pawns() {
        when(mockPawn.getXCoordinate()).thenReturn(0,1,2,3,4,5,6,7,0,1);
        when(mockPawn.getYCoordinate()).thenReturn(0,0,0,0,0,0,0,0,1,1);
        for (int i = 0; i < 10; i++) {
            boolean piecePlaced = testSubject.addPiece(mockPawn);
            if(i < 8) {
                assertTrue(piecePlaced);
            }
            else {
                assertFalse(piecePlaced);
            }
        }
    }

    @Test
    public void testMovePiece_False_When_Piece_Is_Null() {
        boolean pieceMoved = testSubject.movePiece(null, 0, 0);
        assertFalse(pieceMoved);
    }

    @Test
    public void testMovePiece_False_When_Piece_Not_On_Board() {
        when(mockPawn.getXCoordinate()).thenReturn(5);
        when(mockPawn.getYCoordinate()).thenReturn(6);
        boolean pieceMoved = testSubject.movePiece(mockPawn, 5, 5);
        assertFalse(pieceMoved);
        verify(mockPawn, times(1)).getXCoordinate();
        verify(mockPawn, times(1)).getYCoordinate();
    }

    @Test
    public void testMovePiece_True_When_Piece_On_Board_And_Space_Free() {
        when(mockPawn.getXCoordinate()).thenReturn(5);
        when(mockPawn.getYCoordinate()).thenReturn(6);
        assertTrue(testSubject.addPiece(mockPawn));
        boolean pieceMoved = testSubject.movePiece(mockPawn, 5, 5);
        assertTrue(pieceMoved);
        verify(mockPawn, times(3)).getXCoordinate();
        verify(mockPawn, times(3)).getYCoordinate();
    }

    @Test
    public void testMovePiece_False_When_Piece_On_Board_But_Space_Occupied() {
        when(mockPawn.getXCoordinate()).thenReturn(5);
        when(mockPawn.getYCoordinate()).thenReturn(5,6);
        assertTrue(testSubject.addPiece(mockPawn));
        assertTrue(testSubject.addPiece(mockPawn));
        boolean pieceMoved = testSubject.movePiece(mockPawn, 5, 5);
        assertFalse(pieceMoved);
        verify(mockPawn, times(3)).getXCoordinate();
        verify(mockPawn, times(3)).getYCoordinate();
    }
}