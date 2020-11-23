package com.solarwindsmsp.chess;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class Pawn {

    private final PieceColor pieceColor;
    private final ChessBoard chessBoard;

    private int xCoordinate;
    private int yCoordinate;

    public Pawn(PieceColor pieceColor, ChessBoard chessBoard) {
        this.pieceColor = pieceColor;
        this.chessBoard = chessBoard;
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (!this.isLegalMove(movementType, newX, newY)) {
            return;
        }

        this.chessBoard.removePiece(this.xCoordinate, this.yCoordinate);
        this.chessBoard.removePiece(newX, newY);
        this.chessBoard.addPiece(this, newX, newY, this.pieceColor);
    }

    private boolean isLegalMove(MovementType movementType, int newX, int newY) {
        if (!this.chessBoard.isLegalBoardPosition(newX, newY)) {
            return false;
        }

        if (movementType.equals(MovementType.CAPTURE) && this.chessBoard.piecePresent(this.pieceColor.opposite(), newX, newY)) {
            return (this.pieceColor.equals(PieceColor.WHITE) && newY == this.yCoordinate + 1 && (newX == this.xCoordinate - 1 || newX == this.xCoordinate + 1)) ||
                    (this.pieceColor.equals(PieceColor.BLACK) && newY == this.yCoordinate - 1 && (newX == this.xCoordinate - 1 || newX == this.xCoordinate + 1));
        }

        if (this.pieceColor.equals(PieceColor.WHITE)) {
            return newX == this.xCoordinate && (newY == this.yCoordinate + 1 || this.yCoordinate == 1 && newY == this.yCoordinate + 2);
        } else {
            return newX == this.xCoordinate && (newY == this.yCoordinate - 1 || this.yCoordinate == 6 && newY == this.yCoordinate - 2);
        }
    }

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
