package com.solarwindsmsp.chess;

/**
 * Class to define a chess board of 8x8 grid.
 */
public class ChessBoard {

    public static final int MIN_BOARD_INDEX = 0;
    public static final int MAX_BOARD_INDEX = 7;
    private static final int INVALID_INDEX = -1;

    private final Pawn[][] pieces;

    /**
     * Constructor used to create the chess board
     */
    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_INDEX + 1][MAX_BOARD_INDEX + 1];
    }

    /**
     * Checks if the coordinates are within the limits of the chess board
     *
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @return if the coordinates are within the limits of the chess board
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return (xCoordinate >= MIN_BOARD_INDEX && xCoordinate <= MAX_BOARD_INDEX) &&
                (yCoordinate >= MIN_BOARD_INDEX && yCoordinate <= MAX_BOARD_INDEX);
    }

    /**
     * Chess Piece is added on the board only if:
     * - legal coordinates are given
     * - the space from the given coordinates is empty
     * - the color of the chess piece matches the color given
     *
     * @param pawn        pawn that would be added on the board
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @param pieceColor  color of the chess piece
     */
    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        setInvalidCoordinates(pawn);

        if (isLegalBoardPosition(xCoordinate, yCoordinate)) {
            if (spaceIsEmpty(xCoordinate, yCoordinate)) {
                if (pieceColor.equals(pawn.getPieceColor())) {
                    setValidCoordinates(pawn, xCoordinate, yCoordinate);

                    pieces[xCoordinate][yCoordinate] = pawn;
                }
            }
        }
    }

    private void setInvalidCoordinates(Pawn pawn) {
        pawn.setXCoordinate(INVALID_INDEX);
        pawn.setYCoordinate(INVALID_INDEX);
    }

    private void setValidCoordinates(Pawn pawn, int xCoordinate, int yCoordinate) {
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        pawn.setChessBoard(this);
    }

    /**
     * Checks if the space at given coordinates is empty
     *
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @return if space at given coordinates is empty
     */
    public boolean spaceIsEmpty(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate] == null;
    }
}
