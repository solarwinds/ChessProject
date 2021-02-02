package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardTest {

    private ChessBoard testSubject;

    @Mock
    private MovementStrategyFactory movementStrategyFactory;

    @Mock
    private MovementStrategy movementStrategy;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
        testSubject.setStrategyFactory(movementStrategyFactory);
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
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
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.isLegalBoardPosition(5, -1);
        assertFalse(isValidPosition);
    }

    @Test
    public void testAvoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.addPiece(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.addPiece(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.addPiece(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public void testShouldMovePiece(){
        when(movementStrategyFactory.getMovementStrategy(any())).thenReturn(movementStrategy);
        when(movementStrategy.canMove(any(), anyInt(), anyInt())).thenReturn(true);
        Pawn pawn = new Pawn(PieceColor.BLACK);
        testSubject.addPiece(pawn, 0, 0, PieceColor.BLACK);
        testSubject.movePiece(pawn, 1,1);

        assertEquals(1, pawn.getXCoordinate());
        assertEquals(1, pawn.getYCoordinate());

    }
}