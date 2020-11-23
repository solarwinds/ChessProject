package com.solarwindsmsp.chess;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public abstract class Piece {

    protected final PieceColor pieceColor;
    protected final ChessBoard chessBoard;

    protected int xCoordinate;
    protected int yCoordinate;

    public Piece(PieceColor pieceColor, ChessBoard chessBoard) {
        this.pieceColor = pieceColor;
        this.chessBoard = chessBoard;
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (!isLegalMove(movementType, newX, newY)) {
            return;
        }

        chessBoard.removePiece(this.xCoordinate, this.yCoordinate);
        chessBoard.removePiece(newX, newY);
        chessBoard.addPiece(this, newX, newY, this.pieceColor);
    }

    protected abstract boolean isLegalMove(MovementType movementType, int newX, int newY);

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();

        return MessageFormat.format(
                "Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}",
                eol,
                xCoordinate,
                yCoordinate,
                pieceColor
        );
    }
}
