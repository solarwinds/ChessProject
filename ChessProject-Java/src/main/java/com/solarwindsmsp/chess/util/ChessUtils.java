package com.solarwindsmsp.chess.util;

import com.solarwindsmsp.chess.board.ChessBoard;

/**
 * Contains utility functions for the game of chess.
 */
public class ChessUtils {

    /**
     * Transform the coordinates into algebraic notation. e.g. [0,0] is A1
     * @param row the row to be transformed into a number (1...9)
     * @param column the column to be transformed into a letter (A...H)
     * @return the algebraic notation for the square (e.g. A1)
     */
    public static String coordinateToAlgebraic(int row, int column) {
        if (isValidCoordinate(row, column)) {
            return String.format("%s%d", (column > -1 && column < 9 ? String.valueOf((char) (column + 65)) : ""), (row + 1));
        }

        return "";
    }

    /**
     * Determine if the requested coordinates are within the game board
     * @param row the X coordinate
     * @param column the Y coordinate
     * @return true if legal position, false otherwise.
     */
    public static boolean isValidCoordinate(int row, int column) {
        return (row >= 0 && row <= ChessBoard.MAX_BOARD_HEIGHT) &&
                (column >= 0 && column <= ChessBoard.MAX_BOARD_WIDTH);
    }

}
