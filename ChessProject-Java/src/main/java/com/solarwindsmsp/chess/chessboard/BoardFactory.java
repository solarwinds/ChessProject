package com.solarwindsmsp.chess.chessboard;

import com.solarwindsmsp.chess.chessboard.square.Square;
import com.solarwindsmsp.chess.chessboard.square.SquareColor;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_HEIGHT;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_WIDTH;

final class BoardFactory {

    static Square[][] create() {
        //Array index starts at 0 so + 1 for array initialisation
        Square[][] board = new Square[MAX_BOARD_HEIGHT + 1][MAX_BOARD_WIDTH + 1];

        for (int x = 0; x <= MAX_BOARD_WIDTH; x++) {
            for (int y = 0; y <= MAX_BOARD_HEIGHT; y ++) {
                boolean evenRow = x % 2 == 0;
                boolean evenCol = y % 2 == 0;
                if (evenRow) {
                    if (evenCol) {
                        board[x][y] = new Square(SquareColor.BLACK);
                    } else {
                        board[x][y] = new Square(SquareColor.WHITE);
                    }
                } else {
                    if (evenCol) {
                        board[x][y] = new Square(SquareColor.WHITE);
                    } else {
                        board[x][y] = new Square(SquareColor.BLACK);
                    }

                }
            }
        }

        return board;
    }
}