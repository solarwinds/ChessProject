package com.solarwindsmsp.chess;

/**
 * Class to represent a single chessboard.
 *
 * Chess piece placement is represented as an X coordinate and a Y coordinate, both indexed from 0.
 * The X coordinate is the row index ("rank"), and the Y coordinate is the column index ("file").
 */
public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private static int MAX_PAWNS_ALLOWED = MAX_BOARD_WIDTH;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    /**
     * Add a pawn to the board.
     *
     * @param pawn          the pawn to be added
     * @param xCoordinate   X coordinate
     * @param yCoordinate   Y coordinate
     * @param pieceColor    piece colour (overwriting whatever colour the piece already was)
     */
    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!IsLegalBoardPosition(xCoordinate, yCoordinate)
                || isOccupied(xCoordinate, yCoordinate)
                || numberOfPawns(pieceColor) >= MAX_PAWNS_ALLOWED) {
            xCoordinate = yCoordinate = -1;
        } else {
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        pawn.setChessBoard(this);
        pawn.setPieceColor(pieceColor);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
    }

    /**
     * Check if a specified pair of coordinates are valid.
     *
     * @param xCoordinate   X coordinate
     * @param yCoordinate   Y coordinate
     * @return true if these coordinates are contained within the board, otherwise false.
     */
    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 0 || xCoordinate >= MAX_BOARD_WIDTH) {
            return false;
        }
        if (yCoordinate < 0 || yCoordinate >= MAX_BOARD_HEIGHT) {
            return false;
        }
        return true;
    }

    /**
     * Fetch the piece at the specified coordinates.
     *
     * @param xCoordinate   X coordinate (must be valid)
     * @param yCoordinate   Y coordinate (must be valid)
     * @return the piece object at the given coordinates, otherwise null.
     */
    public Pawn pieceAt(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate];
    }

    /**
     * Check if there is already a piece placed at the specified pair of coordinates.
     *
     * @param xCoordinate   X coordinate (must be valid)
     * @param yCoordinate   Y coordinate (must be valid)
     * @return true if there is a piece already placed at this position, otherwise false.
     */
    public boolean isOccupied(int xCoordinate, int yCoordinate) {
        return pieceAt(xCoordinate, yCoordinate) != null;
    }


    /**
     * Change the position of an existing piece on the board.
     *
     * @param pawn      The piece to be moved
     * @param newX      New X coordinate
     * @param newY      New Y coordinate
     */
    public void ChangePosition(Pawn pawn, int newX, int newY) {
        pieces[pawn.getXCoordinate()][pawn.getYCoordinate()] = null;
        pieces[newX][newY] = pawn;
    }

    /*
     * Count the number of pawns (of a particular colour) currently placed on the board.
     */
    private int numberOfPawns(PieceColor pieceColor) {
        int nPawns = 0;
        for (Pawn[] rows: pieces) {
            for (Pawn pawn: rows) {
                if (pawn != null && pawn.getPieceColor() == pieceColor) {
                    nPawns++;
                }
            }
        }
        return nPawns;
    }

}
