package com.solarwindsmsp.chess.util;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.exception.InvalidCoordinateException;

/**
 * Contains utility functions for the game of chess.
 */
public class ChessUtils {

    /**
     * Transform the coordinates into algebraic notation. e.g. [0,0] is A1
     * @param row the row to be transformed into a letter (A...H)
     * @param column the column to be transformed into a number (1...9)
     * @return the algebraic notation for the square (e.g. A1)
     */
    public static String coordinateToAlgebraic(int row, int column) {
        if (isValidCoordinate(row, column)) {
            return String.format("%s%d", (row > -1 && row < 9 ? String.valueOf((char) (row + 65)) : ""), (column + 1));
        }

        throw new InvalidCoordinateException(row, column);
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
