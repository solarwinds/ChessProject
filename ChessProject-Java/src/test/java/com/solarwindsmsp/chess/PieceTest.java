package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class PieceTest {

    // Class under test
    private Piece testSubject;

    // Mocks used for test
    private ChessBoard mockChessBoard;

    @Before
    public void setUp() {
        testSubject = new Piece(PieceColor.BLACK);
        mockChessBoard = mock(ChessBoard.class);
    }

    @Test
    public void testAddToBoard_False_When_No_Chessboard_Set() {
        boolean addedToBoard = testSubject.addToBoard();
        assertFalse(addedToBoard);
    }

    @Test
    public void testAddToBoard_False_When_No_X_Coordinate_Set() {
        testSubject.setChessBoard(mockChessBoard);
        boolean addedToBoard = testSubject.addToBoard();
        assertFalse(addedToBoard);
        verify(mockChessBoard, never()).addPiece(testSubject);
    }

    @Test
    public void testAddToBoard_False_When_No_Y_Coordinate_Set() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(0);
        boolean addedToBoard = testSubject.addToBoard();
        assertFalse(addedToBoard);
        verify(mockChessBoard, never()).addPiece(testSubject);
    }

    @Test
    public void testAddToBoard_True_When_Space_Empty() {
        when(mockChessBoard.addPiece(testSubject)).thenReturn(true);
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(0);
        testSubject.setYCoordinate(0);
        boolean addedToBoard = testSubject.addToBoard();
        assertTrue(addedToBoard);
        verify(mockChessBoard, times(1)).addPiece(testSubject);
    }

    @Test
    public void testAddToBoard_False_When_Space_Full() {
        when(mockChessBoard.addPiece(testSubject)).thenReturn(false);
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(0);
        testSubject.setYCoordinate(0);
        boolean addedToBoard = testSubject.addToBoard();
        assertFalse(addedToBoard);
        verify(mockChessBoard, times(1)).addPiece(testSubject);
    }
}
