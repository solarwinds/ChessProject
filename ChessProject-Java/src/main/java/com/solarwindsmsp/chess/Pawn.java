package com.solarwindsmsp.chess;

import java.text.MessageFormat;

/**
 * Class for representing a single pawn chesspiece.
 *
 * To do anything with the pawn, it must have a {@link ChessBoard} associated with it,
 * and it must be placed at a valid position on that board.
 */
public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    /**
     * Move this piece to a new position on the board.
     *
     * The piece must already be placed on the board, the new coordinates must be valid,
     * and the target square must be unoccupied.
     * If the movement is disallowed, this method silently does nothing.
     *
     * @param movementType      movement type (MOVE or CAPTURE)
     * @param newX              new X coordinate
     * @param newY              new Y coordinate
     */
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

    /*
     * Check if a movement is valid, according to the pawn movement rules.
     *
     * A Pawn can move forward (towards column 0 if black, or towards column 7 if white) by one square,
     * with the single exception that a pawn that is still at its "start position" (the row with column
     * index of 6 if black, or 1 if white) then it is allowed to move forward by two squares.
     */
    private boolean canMove(int newX, int newY) {
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

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return MessageFormat.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
