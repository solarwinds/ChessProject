package com.solarwindsmsp.chess;

/**
 * Entry point into the chess program.
 *
 * @author Sabine Lisa Högl
 */
public class ChessProgram {

    /**
     * Create a new chess board on start up with a default setup.
     *
     * @param args command line arguments supplied
     */
    public static void main(String[] args) {

        ChessBoard chessBoard = new ChessBoard();

        // set up board with default pawns; in future allow for loading of special setup
        chessBoard.setUpDefaultBoard();

        // overview of initial setup
        chessBoard.printChessBoard();

        // example moves to show the altered board
        chessBoard.movePiece(1, 2, 1, 4, MovementType.MOVE);
        chessBoard.movePiece(5, 2, 5, 3, MovementType.MOVE);
        chessBoard.movePiece(2, 7, 2, 5, MovementType.MOVE);

        chessBoard.printChessBoard();

    }
}
