package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

public class Pawn extends ChessPiece {

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
}
