package com.solarwindsmsp.chess;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for ChessGame class
 */
public class ChessGameTest {
    private ChessGame game;

    @Before
    public void setUp() throws Exception {
        game = new ChessGame();
    }

    @Test
    public void testAdd_A_Pawn() throws InvalidPlacementException {
        Piece pawn = game.add(Pawn.class, 0, 0, PieceColor.BLACK);
        assertTrue(pawn instanceof Pawn);
        assertTrue(game.getChessBoard().allPieces().contains(pawn));
    }

}
