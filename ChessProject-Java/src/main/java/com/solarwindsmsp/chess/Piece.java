package com.solarwindsmsp.chess;

/**
 * An abstract base class which represents all chess pieces.
 *
 * Each subclass will implement a toString() method returning a single-character case-significant String
 * indicating its type:
 *
 * P = White Pawn
 * R
 * H = White Horse
 * B
 * Q
 * K
 *
 * p = Black Pawn
 * r
 * h
 * b
 * q
 * k
 *
 */
public abstract class Piece {
    protected ChessBoard chessBoard;
    protected int xCoordinate;
    protected int yCoordinate;
    protected PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
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

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    /**
     * Relocate a Piece from its current board position to the supplied new position as either a move or a capture
     * operation.
     *
     * @param movementType is this a move or a capture?
     * @param newX new coordinate
     * @param newY new coordinate
     */
    public abstract void move(MovementType movementType, int newX, int newY);
}
