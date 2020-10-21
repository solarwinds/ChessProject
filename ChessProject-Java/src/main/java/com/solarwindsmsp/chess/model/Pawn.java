package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.PieceColor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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

    public void setCoordinates(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void move(MovementType movementType, int newX, int newY) {
        log.info("Move: type {} x {} y {}", movementType, newX, newY);

        if (this.isLegalMove(movementType, newX, newY)) {
            log.info("Move is legal.");
            this.chessBoard.getPieces()[this.xCoordinate][this.yCoordinate] = null;
            this.xCoordinate = newX;
            this.yCoordinate = newY;
            this.chessBoard.getPieces()[this.xCoordinate][this.yCoordinate] = this;
        }
    }

    public boolean isLegalMove(MovementType movementType, int newX, int newY) {
        log.info("Is legal move: color {} move type {} oldX {} oldY {} newX {} newY {}",
                this.pieceColor, movementType, this.xCoordinate, this.yCoordinate, newX, newY);

        if (!this.chessBoard.isLegalBoardPosition(newX ,newY)) {
            return false;
        }

        int advanceX = newX - this.xCoordinate;
        int advanceY = newY - this.yCoordinate;
        // BLACK moves downwards only, WHITE upwards only
        if (advanceY > 0 && this.pieceColor == PieceColor.BLACK
                || advanceY < 0 && this.pieceColor == PieceColor.WHITE) {
            return false;
        }
        int deltaX = Math.abs(advanceX);
        int deltaY = Math.abs(advanceY);

        switch (movementType) {
            case MOVE: {
                if (deltaX != 0 || !this.chessBoard.isEmptyAtCoordinates(newX, newY)) {
                    return false;
                }
                if (this.chessBoard.isEmptyAtCoordinates(newX, newY) && (deltaY == 1
                        || (deltaY == 2
                        // can jump 2 spaces only if in initial position
                            && this.yCoordinate == (this.pieceColor == PieceColor.WHITE ? 1 : 6)
                            && this.chessBoard.isEmptyAtCoordinates(this.yCoordinate + 1, newY)))) {
                    return true;
                }
                break;
            }
            case CAPTURE: {
                if (deltaY == 1 && deltaX == 1
                        && !this.chessBoard.isEmptyAtCoordinates(newX, newY)
                        // cannot attack same color
                        && this.pieceColor != this.chessBoard.getPieces()[newX][newY].getPieceColor()) {
                    return true;
                }
                break;
            }
            default: throw new IllegalStateException("Movement type must be either MOVE or CAPTURE.");
        }

        return false;
    }

    @Override
    public boolean isLegalInitialCoordinates(int x, int y) {
        return x >= 0 && x <= ChessBoard.MAX_BOARD_WIDTH
                    && ((this.pieceColor == PieceColor.WHITE && y == 1)
                        || (this.pieceColor == PieceColor.BLACK && y == ChessBoard.MAX_BOARD_HEIGHT - 1));
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
