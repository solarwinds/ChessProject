package com.solarwindsmsp.chess;

/**
 * Class to define a Pawn chess piece
 */
public class Pawn {

    private final PieceColor pieceColor;
    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Constructor used to set the color of the pawn
     *
     * @param pieceColor color of the pawn
     */
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

    /**
     * Moves the pawn in the right direction, based on the color
     *
     * @param movementType the movement of the pawn
     * @param newX         new x coordinate of the pawn
     * @param newY         new y coordinate of the pawn
     */
    public void move(MovementType movementType, int newX, int newY) {
        if (MovementType.MOVE.equals(movementType)) {
            if (PieceColor.BLACK.equals(this.pieceColor)) {
                if (yCoordinate == newY && newX == this.xCoordinate - 1) {
                    this.xCoordinate = newX;
                }
            } else {
                if (yCoordinate == newY && newX == this.xCoordinate + 1) {
                    this.xCoordinate = newX;
                }
            }

        } else if (MovementType.CAPTURE.equals(movementType)) {
            throw new UnsupportedOperationException("Need to implement CAPTURE move");
        }
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    private String getCurrentPositionAsString() {
        String eol = System.lineSeparator();

        return String.format("Current X: %d%sCurrent Y: %d%sPiece Color: %s", xCoordinate, eol, yCoordinate, eol, pieceColor);
    }
}
