package com.solarwindsmsp.chess;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor, ChessBoard chessBoard) {
        super(pieceColor, chessBoard);
    }

    protected boolean isLegalMove(MovementType movementType, int newX, int newY) {
        if (!chessBoard.isLegalBoardPosition(newX, newY)) {
            return false;
        }

        if (movementType.equals(MovementType.CAPTURE) && chessBoard.piecePresent(pieceColor.opposite(), newX, newY)) {
            return (pieceColor.equals(PieceColor.WHITE) && newY == yCoordinate + 1 && (newX == xCoordinate - 1 || newX == xCoordinate + 1)) ||
                    (pieceColor.equals(PieceColor.BLACK) && newY == yCoordinate - 1 && (newX == xCoordinate - 1 || newX == xCoordinate + 1));
        }

        if (this.pieceColor.equals(PieceColor.WHITE)) {
            return newX == xCoordinate && (newY == yCoordinate + 1 || yCoordinate == 1 && newY == yCoordinate + 2);
        } else {
            return newX == xCoordinate && (newY == yCoordinate - 1 || yCoordinate == 6 && newY == yCoordinate - 2);
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
