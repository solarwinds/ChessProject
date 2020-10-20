package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.PieceColor;
import lombok.Data;

@Data
public class Pawn implements ChessPiece {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Pawn(PieceColor pieceColor, int xCoordinate, int yCoordinate) {
        this.pieceColor = pieceColor;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (this.isLegalMove(movementType, newX, newY)) {
            this.chessBoard.getPieces()[this.xCoordinate][this.yCoordinate] = null;
            this.xCoordinate = newX;
            this.yCoordinate = newY;
            this.chessBoard.getPieces()[this.xCoordinate][this.yCoordinate] = this;
        } else {
            throw new IllegalStateException("Move is illegal.") ;
        }
    }

    public boolean isLegalMove(MovementType movementType, int newX, int newY) {
        if (!this.chessBoard.isLegalBoardPosition(newX ,newY)) {
            return false;
        }

        int advanceX = newX - this.xCoordinate;
        int deltaY = Math.abs(newY - this.yCoordinate);

        switch (movementType) {
            case MOVE: {
                if (deltaY != 0 || !this.chessBoard.isEmptyAtCoordinates(newX, newY)) {
                    return false;
                }
                if (advanceX == 1
                        || (advanceX == 2
                            && this.xCoordinate == 1
                            && this.chessBoard.isEmptyAtCoordinates(this.xCoordinate + 1, newY))) {
                    return true;
                }
                break;
            }
            case CAPTURE: {
                if (deltaY == 1 && advanceX == 1 && !this.chessBoard.isEmptyAtCoordinates(newX, newY)) {
                    return true;
                }
                break;
            }
            default: throw new IllegalStateException("Movement type must be either MOVE or CAPTURE.");
        }

        return false;
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
