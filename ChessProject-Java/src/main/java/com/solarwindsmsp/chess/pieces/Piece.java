package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.exceptions.InvalidMoveException;

/**
 * Abstract class for pieces
 */
public abstract class Piece {

    protected ChessBoard chessBoard;
    protected int xCoordinate;
    protected int yCoordinate;
    protected PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }

    /**
     * Move piece into new position
     * @param movementType Movement Type
     * @param newX Row of new position
     * @param newY Column of new position
     */
    public void move(MovementType movementType, int newX, int newY) throws InvalidMoveException {
        if (isValidMoveForPiece(movementType, newX, newY) && isValidMoveForChessBoard(movementType, newX, newY)) {
            chessBoard.commitMove(this, newX, newY);
        } else {
            throw new InvalidMoveException(this, newX, newY, movementType);
        }
    }

    /**
     * Get the maximum number of pieces of the same color for a Chess Board
     * @return maximum number of pieces
     */
    public abstract int getMaxNumberOfPieces();

    /**
     * Decides if move is valid for a certain piece
     * @param movementType Movement Type
     * @param newX Row of new position
     * @param newY Column of new position
     * @return if piece move is valid
     */
    abstract boolean isValidMoveForPiece(MovementType movementType, int newX, int newY);

    /**
     * Decides if move is valid for a certain chessboard configuration (assuming piece move is valid)
     * @param movementType Movement Type
     * @param newX Row of new position
     * @param newY Column of new position
     * @return if chessboard move is valid
     */
    abstract boolean isValidMoveForChessBoard(MovementType movementType, int newX, int newY);

    public void assignPieceToChessBoard(ChessBoard chessBoard, int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.chessBoard = chessBoard;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }

}
