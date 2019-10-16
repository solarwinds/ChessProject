package com.solarwindsmsp.chess.chessboard;

import org.junit.Test;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_HEIGHT;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_WIDTH;
import static junit.framework.TestCase.assertSame;

public class BoardFactoryTest {

    @Test
    public void test_Create_Builds_Correctly_Patterned_Board() {
        Square[][] testBoard = BoardFactory.create();

        for (int x = 0; x <= MAX_BOARD_WIDTH; x++) {
            for (int y = 0; y <= MAX_BOARD_HEIGHT; y++) {
                boolean evenRow = x % 2 == 0;
                boolean evenCol = y % 2 == 0;
                Square selectedSquare = testBoard[x][y];
                if (evenRow) {
                    if (evenCol) {
                        assertSame(selectedSquare.getSquareColor(), SquareColor.BLACK);
                    } else {
                        assertSame(selectedSquare.getSquareColor(), SquareColor.WHITE);
                    }
                } else {
                    if (evenCol) {
                        assertSame(selectedSquare.getSquareColor(), SquareColor.WHITE);
                    } else {
                        assertSame(selectedSquare.getSquareColor(), SquareColor.BLACK);
                    }
                }
            }
        }
    }
}
