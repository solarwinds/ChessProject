package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;
import com.solarwindsmsp.chess.util.ChessUtils;

import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * The abstract ChessPiece class that all defined pieces will inherit from.
 */
public abstract class ChessPiece {

    public static final Logger logger = Logger.getLogger(ChessPiece.class.getName());

    private int rowCoordinate;
    private int colCoordinate;
    private final PieceColor pieceColor;
    private PieceType pieceType;

    public ChessPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public void setRowCoordinate(int value) {
        this.rowCoordinate = value;
    }

    public int getColCoordinate() {
        return colCoordinate;
    }

    public void setColCoordinate(int value) {
        this.colCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    protected void setPieceType(PieceType type) {
        this.pieceType = type;
    }

    public void removeFromBoard() {
        this.setRowCoordinate(-1);
        this.setColCoordinate(-1);
    }

    public void move(MovementType movementType, int newRow, int newCol) {
        logger.info(MessageFormat.format("Attempting to move from {0} to {1}",
                ChessUtils.coordinateToAlgebraic(this.getRowCoordinate(), this.getColCoordinate()),
                ChessUtils.coordinateToAlgebraic(newRow, newCol)));

        if (isValidMove(newRow, newCol)) {
            logger.info("Valid move requested!");
            this.setRowCoordinate(newRow);
            this.setColCoordinate(newCol);
        } else {
            logger.severe("Invalid movement requested.");
        }
    }

    public abstract boolean isValidMove(int newRow, int newCol);

    @Override
    public String toString() {
        String algebraicNotation = ChessUtils.coordinateToAlgebraic(this.rowCoordinate, this.colCoordinate);
        return MessageFormat.format("Algebraic Notation: {1}{0}Current Column: {2}{0}Current Row: {3}{0}Piece Color: {4}", System.lineSeparator(), algebraicNotation, colCoordinate, rowCoordinate, pieceColor);
    }
}
