package com.solarwindsmsp.chess;

/**
 * Pawn class to hold logic specifically pertaining to the pawn.
 */
public class Pawn extends Piece {

    public static final int MAX_NUMBER_OF_BLACK_PAWNS = 8;
    public static final int MAX_NUMBER_OF_WHITE_PAWNS = 8;
    public static int numberOfWhitePawns = 0;
    public static int numberOfBlackPawns = 0;

    /**
     * It is assumed that a piece cannot exist without a chess board, and coordinates.
     *
     * @param pieceColor  chosen piece colour
     * @param xCoordinate position along the x axis for the piece
     * @param yCoordinate position along the y axis for the piece
     * @param chessBoard  current chess board
     */
    public Pawn(PieceColor pieceColor, int xCoordinate, int yCoordinate, ChessBoard chessBoard) {
        super(pieceColor, xCoordinate, yCoordinate, chessBoard);
        if (pieceColor == PieceColor.WHITE) {
            numberOfWhitePawns++;
        } else if (pieceColor == PieceColor.BLACK) {
            numberOfBlackPawns++;
        }
    }

    @Override
    /**
     * Polymorphically called from Piece superclass. This implementation considers that pawns can initially
     * move two squares forward.
     */
    public boolean move(MovementType movementType, int newX, int newY) {

        if (movementType == MovementType.CAPTURE) {
            throw new UnsupportedOperationException("Need to implement Pawn.move() for CAPTURE");
        }

        if (chessBoard.isLegalBoardPosition(newX, newY)) {
            // the chosen square is on the board
            if (chessBoard.isSquareFree(newX, newY)) {
                // the chosen square is free
                if (this.getPieceColor() == PieceColor.WHITE) {
                    if (xCoordinate == newX && yCoordinate + 1 == newY) {
                        updateCoords(newX, newY);
                        // the piece is moved by one square to the new coordinates
                        return true;
                    } else if (xCoordinate == newX && (yCoordinate == ChessBoard.WHITE_PAWN_RANK && yCoordinate + 2 == newY)) {
                        if (chessBoard.isSquareFree(xCoordinate, yCoordinate + 1)) {
                            updateCoords(newX, newY);
                            // the piece is moved by two squares to the new coordinates
                            return true;
                        }
                    } else {
                        System.err.println("Illegal move attempted by " + this.pieceColor() + " " + this.pieceName() +
                                " at " + "(" + xCoordinate + "," + yCoordinate + ")" + " to "
                                + "(" + newX + "," + newY + ")" + ".");
                        return false;
                    }
                } else if (this.getPieceColor() == PieceColor.BLACK) {
                    if (xCoordinate == newX && yCoordinate - 1 == newY) {
                        updateCoords(newX, newY);
                        return true;
                    } else if (xCoordinate == newX && (yCoordinate == ChessBoard.BLACK_PAWN_RANK && yCoordinate - 2 == newY)) {
                        if (chessBoard.isSquareFree(xCoordinate, yCoordinate - 1)) {
                            updateCoords(newX, newY);
                            return true;
                        }
                    } else {
                        System.err.println("Illegal move attempted by " + this.pieceColor() + " " + this.pieceName() +
                                " at " + "(" + xCoordinate + "," + yCoordinate + ")" + " to "
                                + "(" + newX + "," + newY + ")" + ".");
                        return false;
                    }
                }
            } else {
                System.err.println("The chosen square is already occupied.");
                return false;
            }
        } else {
            System.err.println(this.pieceName() + " cannot be placed out of bounds of the board.");
            return false;
        }
        return false;
    }

    @Override
    /**
     * Polymorphically called from Piece superclass
     */
    public String toString() {
        if (getPieceColor() == PieceColor.WHITE) {
            return "p";
        } else {
            return "P";
        }
    }

    /**
     * Get the piece name of a specific type.
     *
     * @return piece name
     */
    @Override
    public String pieceName() {
        return "pawn";
    }

    /**
     * Get the colour for a piece.
     *
     * @return piece colour
     */
    @Override
    public String pieceColor() {
        String pieceColor = "white";
        if (getPieceColor() == PieceColor.BLACK) {
            pieceColor = "black";
        }
        return pieceColor;
    }

    @Override
    /**
     * Check if the maximum number of respective pawns is already on the board.
     */
    protected boolean isMaxNumberExceeded() {
        if (this.pieceColor == PieceColor.WHITE) {
            return numberOfWhitePawns > MAX_NUMBER_OF_WHITE_PAWNS;
        } else {
            return numberOfBlackPawns > MAX_NUMBER_OF_BLACK_PAWNS;
        }
    }
}