package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.*;

public abstract class Piece {

    private ChessBoard chessBoard;
    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
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

    public void move(MovementType movementType, int newX, int newY) throws IllegalMoveException {
        validateMove(movementType, newX, newY);
        getChesssBoard().placePiece(this, newX, newY);
    }

    private void validateMove(MovementType movementType, int newX, int newY) throws IllegalMoveException {
        if (!getChesssBoard().isLegalBoardPosition(newX, newY)) {
            throw new IllegalMoveException("Not legal board position");
        }

        if (movementType == null) {
            throw new IllegalMoveException("Movement type not specified");
        }

        if (movementType == MovementType.MOVE) {
            if (!getChesssBoard().isPositionAvailable(newX, newY)) {
                throw new IllegalMoveException("Position Not available");
            }
        } else if (movementType == MovementType.CAPTURE) {
            Piece targetPiece = getChesssBoard().getPiece(xCoordinate, yCoordinate);
            if (targetPiece == null || (targetPiece.getPieceColor() == this.getPieceColor())) {
                throw new IllegalMoveException("Invalid capture position");
            }
        }

        if (!isValidTransition(movementType, newX, newY)) {
            throw new IllegalMoveException("Not valid transition");
        }
    }

    public abstract boolean isValidTransition(MovementType movementType, int newX, int newY);

    public boolean IsLegalPiecePosition(int xCoordinate, int yCoordinate) {
        if (!getChesssBoard().isLegalBoardPosition(xCoordinate, yCoordinate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
