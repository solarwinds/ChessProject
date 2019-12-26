package com.solarwindsmsp.chess;

import java.text.MessageFormat;

/**
 * Class for representing a single pawn chesspiece.
 */
public class Pawn extends Piece {

    /**
     * {@inheritDoc}
     *
     * A Pawn can move forward (towards columnIndex 0 if black, or towards columnIndex 7 if white)
     * by one square, with the single exception that a pawn that is still at its "start position"
     * (the row with column index of 6 if black, or 1 if white) then it is allowed to move forward by
     * two squares.
     */
    @Override
    protected boolean canMove(int newX, int newY) {
        int starterColumnIndex = (this.getPieceColor() == PieceColor.BLACK) ? 6 : 1;
        int direction = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;
        int currentX = getXCoordinate();
        int currentY = getYCoordinate();
        if (newX != currentX) {
            return false;
        } else if (currentY == starterColumnIndex) {
            return newY == currentY + direction || newY == currentY + (direction * 2);
        } else {
            return newY == currentY + direction;
        }
    }
}
