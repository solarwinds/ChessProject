package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

public class Pawn extends ChessPiece {

    private static final int ALLOWED_BLACK_ROW = 6;
    private static final int ALLOWED_WHTIE_ROW = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        setPieceType(PieceType.PAWN);
    }

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

    @Override
    public boolean isValidSpaceToAdd(int row, int col) {
        return row == ((this.getPieceColor() == PieceColor.WHITE) ? ALLOWED_WHTIE_ROW : ALLOWED_BLACK_ROW);
    }
}
