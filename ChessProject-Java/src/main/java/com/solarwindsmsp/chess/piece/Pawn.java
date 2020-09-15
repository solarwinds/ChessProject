package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

public class Pawn extends ChessPiece {

    private static final int ALLOWED_BLACK_ROW = 6;
    private static final int ALLOWED_WHTIE_ROW = 1;

    /**
     * Constructor for a pawn.
     * @param pieceColor the colour of the piece to be created.
     */
    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        setPieceType(PieceType.PAWN);
    }

    /**
     * Determine if the move is valid for the piece type
     * @param newRow the new row for the piece
     * @param newCol the new column for the piece
     * @return true if valid, false otherwise.
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) {
        /*
         * TODO: Need to consider pawns can move two squares on their first movement,
         *  but only when in Row 7 (Black) or Row 2 (White)
         */
        int allowedMovement = 1;

        if (this.getPieceColor() == PieceColor.WHITE) {
            return (this.getRowCoordinate() + allowedMovement) == newRow;
        } else {
            return (this.getRowCoordinate() - allowedMovement) == newRow;
        }
    }

    /**
     * Determine if piece can be successfully added by validating starting position
     * @param row the row for the piece to be added to
     * @param col the column for the piece to be added to
     * @return true if valid, false otherwise.
     */
    @Override
    public boolean isValidSpaceToAdd(int row, int col) {
        return row == ((this.getPieceColor() == PieceColor.WHITE) ? ALLOWED_WHTIE_ROW : ALLOWED_BLACK_ROW);
    }
}
