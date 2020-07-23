package com.solarwindsmsp.chess.utils;

import com.solarwindsmsp.chess.game.ChessBoard;
import com.solarwindsmsp.chess.pieces.ChessPiece;

import static com.solarwindsmsp.chess.constants.Constants.*;

/**
 * Utility class with helper methods
 */
public class GameUtils {

    /**
     * Setting invalid coordinates to given chess piece
     *
     * @param chessPiece chess piece to whom invalid coordinates are set
     */
    public static void setInvalidCoordinates(ChessPiece chessPiece) {
        chessPiece.setXCoordinate(INVALID_INDEX);
        chessPiece.setYCoordinate(INVALID_INDEX);
        chessPiece.setChessBoard(null);
    }

    /**
     * Setting valid coordinates and chess board to a given chess piece
     *
     * @param chessPiece  chess piece to whom valid coordinates are set
     * @param xCoordinate x coordinate on the chess board
     * @param yCoordinate y coordinate on the chess board
     * @param chessBoard  chess board on which the game is played on
     */
    public static void setValidCoordinatesAndChessBoard (ChessPiece chessPiece, int xCoordinate, int yCoordinate, ChessBoard chessBoard) {
        chessPiece.setXCoordinate(xCoordinate);
        chessPiece.setYCoordinate(yCoordinate);
        chessPiece.setChessBoard(chessBoard);
    }

    /**
     * Checks if the coordinates are within the limits of the chess board
     *
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @return if the coordinates are within the limits of the chess board
     */
    public static boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= MIN_BOARD_INDEX && xCoordinate <= MAX_BOARD_INDEX &&
                yCoordinate >= MIN_BOARD_INDEX && yCoordinate <= MAX_BOARD_INDEX;
    }

    /**
     * Checks if the space at given coordinates is empty
     *
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @return if space at given coordinates is empty
     */
    public static boolean positionIsEmpty(ChessBoard chessBoard, int xCoordinate, int yCoordinate) {
        ChessPiece[][] pieces = chessBoard.getPieces();

        return pieces[xCoordinate][yCoordinate] == null;
    }
}
