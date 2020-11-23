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

        if (isCapture(movementType, newX, newY)) {
            return isValidWhiteCapture(newX, newY) || isValidBlackCapture(newX, newY);
        }

        if (pieceColor.equals(PieceColor.WHITE)) {
            return newX == xCoordinate && (newY == yCoordinate + 1 || yCoordinate == 1 && newY == yCoordinate + 2);
        } else {
            return newX == xCoordinate && (newY == yCoordinate - 1 || yCoordinate == 6 && newY == yCoordinate - 2);
        }
    }

    private boolean isValidBlackCapture(int newX, int newY) {
        return pieceColor.equals(PieceColor.BLACK) &&
                newY == yCoordinate - 1 &&
                (newX == xCoordinate - 1 || newX == xCoordinate + 1);
    }

    private boolean isValidWhiteCapture(int newX, int newY) {
        return pieceColor.equals(PieceColor.WHITE) &&
                newY == yCoordinate + 1 &&
                (newX == xCoordinate - 1 || newX == xCoordinate + 1);
    }

    private boolean isCapture(MovementType movementType, int newX, int newY) {
        return movementType.equals(MovementType.CAPTURE) && chessBoard.piecePresent(pieceColor.opposite(), newX, newY);
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
