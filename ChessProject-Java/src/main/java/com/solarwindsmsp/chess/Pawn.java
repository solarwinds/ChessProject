package com.solarwindsmsp.chess;

import java.text.MessageFormat;

/**
 * Class for representing a single pawn chesspiece.
 */
public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void Move(MovementType movementType, int newX, int newY) {
        ChessBoard board = this.getChessBoard();
        if (board.IsLegalBoardPosition(newX, newY) && !board.isOccupied(newX, newY)) {
            if (movementType == MovementType.MOVE) {
                if (canMove(newX, newY)) {
                    board.ChangePosition(this, newX, newY);
                    this.setXCoordinate(newX);
                    this.setYCoordinate(newY);
                }
            } else {
                // TODO: MovementType.CAPTURE
                throw new UnsupportedOperationException("Need to implement Pawn.Move(CAPTURE)");
            }
        }
    }

    /**
     * {@inheritDoc}
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
