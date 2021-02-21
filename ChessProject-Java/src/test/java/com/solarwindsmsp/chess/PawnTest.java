package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

public class PawnTest {

    // Class under test
    private Pawn testSubject;

    // Mocks used for test
    private ChessBoard mockChessBoard;

    @Before
    public void setUp() {
        testSubject = new Pawn(PieceColor.BLACK);
        mockChessBoard = mock(ChessBoard.class);
    }

    @Test
    public void testMove_False_When_No_ChessBoard_Set() {
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(5, 5);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
    }

    @Test
    public void testMove_False_With_No_Change_In_Coordinates_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(5, 6);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_IllegalCoordinates_Right_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(7);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(8, 6);
        assertFalse(moved);
        assertEquals(7,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_LegalCoordinates_Right_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move( 6, 6);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_IllegalCoordinates_Left_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(0);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move( -1, 6);
        assertFalse(moved);
        assertEquals(0,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_LegalCoordinates_Left_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move( 4, 6);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_IllegalCoordinates_Backwards_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(7);
        boolean moved = testSubject.move( 5, 8);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(7, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_LegalCoordinates_Backwards_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(5, 7);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_False_With_IllegalCoordinates_Forwards_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(0);
        boolean moved = testSubject.move(5, -1);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(0, testSubject.getYCoordinate());
        verify(mockChessBoard).isLegalBoardPosition(5, -1);
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

    @Test
    public void testMove_True_With_LegalCoordinates_Forward_UpdatesCoordinates() {
        when(mockChessBoard.isLegalBoardPosition(5, 5)).thenReturn(true);
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(5, 5);
        assertTrue(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(5, testSubject.getYCoordinate());
        verify(mockChessBoard).isLegalBoardPosition(5,5);
        verify(mockChessBoard).movePiece(testSubject, 5, 5);
    }

    @Test
    public void testMove_False_With_LegalCoordinates_Forwards_But_More_Then_One_Space_DoesNotMove() {
        testSubject.setChessBoard(mockChessBoard);
        testSubject.setXCoordinate(5);
        testSubject.setYCoordinate(6);
        boolean moved = testSubject.move(5, 4);
        assertFalse(moved);
        assertEquals(5,testSubject.getXCoordinate());
        assertEquals(6, testSubject.getYCoordinate());
        verify(mockChessBoard, never()).isLegalBoardPosition(anyInt(), anyInt());
        verify(mockChessBoard, never()).movePiece(any(Pawn.class), anyInt(), anyInt());
    }

}