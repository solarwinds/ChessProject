package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.game.ChessBoard;
import com.solarwindsmsp.chess.game.MovementType;
import com.solarwindsmsp.chess.game.PieceColor;
import com.solarwindsmsp.chess.game.PieceType;

/**
 * Class to define basic attributes and methods of a chess piece
 * <p>
 * Every piece from the chess board should extend this class
 */
public abstract class ChessPiece {

    protected final PieceColor pieceColor;
    protected ChessBoard chessBoard;
    protected int xCoordinate;
    protected int yCoordinate;
    protected PieceType pieceType;

    /**
     * Constructor used to set the color of the chess piece
     *
     * @param pieceColor color of the chess piece
     */
    public ChessPiece(PieceColor pieceColor) {
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

    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Moves the chess piece on the new coordinates
     *
     * @param movementType   the movement type of the chess piece
     * @param newXCoordinate new x coordinate on the board
     * @param newYCoordinate new y coordinate on the board
     */
    public abstract void move(MovementType movementType, int newXCoordinate, int newYCoordinate);

    /**
     * Checks if a chess piece is placed on a valid position at the start of the game
     *
     * @param xCoordinate x coordinate of the piece
     * @return if chess piece is placed on a valid position at the start of the game
     */
    public abstract boolean startsInLegalPosition(int xCoordinate);

    /**
     * Custom implementation of toString
     *
     * @return custom implementation of the object as a string
     */
    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    private String getCurrentPositionAsString() {
        String eol = System.lineSeparator();

        return String.format("Current X: %d%sCurrent Y: %d%sPiece Color: %s", xCoordinate, eol, yCoordinate, eol, pieceColor);
    }
}
