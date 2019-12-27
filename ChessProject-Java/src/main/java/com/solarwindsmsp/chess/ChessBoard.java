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

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    /**
     * Add a new piece to the board.
     *
     * The piece must be a new piece that is not currently on the board.
     *
     * If the coordinates are invalid, or if there is already another piece placed at that
     * position on the board, then the piece will bad added with special "invalid coordinate"
     * values of [-1, -1], but no exception will be raised.
     *
     * @param piece         the piece to be added
     * @param xCoordinate   X coordinate
     * @param yCoordinate   Y coordinate
     * @param pieceColor    piece colour (overwriting whatever colour the piece already was)
     */
    public void add(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor)
            throws InvalidPlacementException {
        if (piece.getChessBoard() != null) {
            throw new InvalidPlacementException("Piece is already placed on a chessboard");
        }
        if (!IsLegalBoardPosition(xCoordinate, yCoordinate)
                || isOccupied(xCoordinate, yCoordinate)
                || ((piece instanceof Pawn) && numberOfPawns(pieceColor) >= MAX_PAWNS_ALLOWED)) {
            piece.setXCoordinate(-1);
            piece.setYCoordinate(-1);
            throw new InvalidPlacementException(String.format("Invalid position [%d, %d]",
                                                              xCoordinate, yCoordinate));
        }
        pieces[xCoordinate][yCoordinate] = piece;
        piece.setChessBoard(this);
        piece.setPieceColor(pieceColor);
        piece.setXCoordinate(xCoordinate);
        piece.setYCoordinate(yCoordinate);
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
    public Piece pieceAt(int xCoordinate, int yCoordinate) {
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
     * @param piece      The piece to be moved
     * @param newX      New X coordinate
     * @param newY      New Y coordinate
     */
    public void ChangePosition(Piece piece, int newX, int newY) {
        pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null;
        pieces[newX][newY] = piece;
    }

    /*
     * Count the number of pawns (of a particular colour) currently placed on the board.
     */
    private int numberOfPawns(PieceColor pieceColor) {
        int nPawns = 0;
        for (Piece[] columns: pieces) {
            for (Piece piece: columns) {
                if ((piece instanceof Pawn) && piece.getPieceColor() == pieceColor) {
                    nPawns++;
                }
            }
        }
        return nPawns;
    }

}
