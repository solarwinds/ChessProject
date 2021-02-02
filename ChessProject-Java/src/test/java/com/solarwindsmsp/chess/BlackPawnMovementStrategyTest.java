package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.solarwindsmsp.chess.PieceColor.BLACK;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlackPawnMovementStrategyTest {

    private MovementStrategy testSubject = new BlackPawnMovementStrategy();

    @Mock
    private ChessBoard chessBoard;

    @Before
    public void before(){
        when(chessBoard.isLegalBoardPosition(anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    public void shouldMoveForward() {
        Pawn pawn = new Pawn(BLACK);
        pawn.setXCoordinate(0);
        pawn.setYCoordinate(1);
        pawn.setChessBoard(chessBoard);

        assertTrue(testSubject.canMove(pawn, 0, 0));
    }

    @Test
    public void shouldNotMoveBackwards() {
        Pawn pawn = new Pawn(BLACK);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(0);
        pawn.setYCoordinate(1);

        assertFalse(testSubject.canMove(pawn, 0, 2));
    }

    @Test
    public void shouldNotMoveLeft() {
        Pawn pawn = new Pawn(BLACK);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(1);
        pawn.setYCoordinate(1);

        assertFalse(testSubject.canMove(pawn, 0, 0));
    }

    @Test
    public void shouldNotMoveRight() {
        Pawn pawn = new Pawn(BLACK);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(1);
        pawn.setYCoordinate(1);

        assertFalse(testSubject.canMove(pawn, 2, 0));
    }
}
