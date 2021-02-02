package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.solarwindsmsp.chess.PieceColor.BLACK;
import static com.solarwindsmsp.chess.PieceColor.WHITE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WhitePawnMovementStrategyTest {

    private WhitePawnMovementStrategy whitePawnMovementStrategy = new WhitePawnMovementStrategy();

    @Mock
    private ChessBoard chessBoard;

    @Before
    public void before(){
        when(chessBoard.isLegalBoardPosition(anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    public void shouldMoveForward() {
        Pawn pawn = new Pawn(WHITE);
        pawn.setXCoordinate(0);
        pawn.setYCoordinate(1);
        pawn.setChessBoard(chessBoard);

        assertTrue(whitePawnMovementStrategy.canMove(pawn, 0, 2));
    }

    @Test
    public void shouldNotMoveBackwards() {
        Pawn pawn = new Pawn(WHITE);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(0);
        pawn.setYCoordinate(1);

        assertFalse(whitePawnMovementStrategy.canMove(pawn, 0, 0));
    }

    @Test
    public void shouldNotMoveLeft() {
        Pawn pawn = new Pawn(WHITE);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(1);
        pawn.setYCoordinate(1);

        assertFalse(whitePawnMovementStrategy.canMove(pawn, 0, 0));
    }

    @Test
    public void shouldNotMoveRight() {
        Pawn pawn = new Pawn(WHITE);
        pawn.setChessBoard(chessBoard);
        pawn.setXCoordinate(1);
        pawn.setYCoordinate(1);

        assertFalse(whitePawnMovementStrategy.canMove(pawn, 2, 0));
    }
}
