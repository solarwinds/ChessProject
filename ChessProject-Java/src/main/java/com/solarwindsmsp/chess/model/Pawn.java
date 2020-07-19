package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.enums.MovementType;
import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.enums.PieceType;

public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor, PieceType pieceType) {
        super(pieceColor, pieceType);
    }

    @Override
    public void move(MovementType move, int newX, int newY) {
        if (isValidDestination(newX, newY)) {
            if (MovementType.MOVE.equals(move)) {
                if (isValidMove(newX, newY)) {
                    registerNewMove(newX, newY);
                } else {
                    System.out.println("Not a valid move");
                }
            }
        } else System.out.println("Not a valid destination");
    }

    private boolean isValidDestination(int newX, int newY) {
        return getChessBoard().isLegalBoardPosition(newX, newY) && !getChessBoard().isCellOccupied(newX, newY);

    }

    private void registerNewMove(int newX, int newY) {
        getChessBoard().getPieces()[getXCoordinate()][getYCoordinate()] = null;
        setXCoordinate(newX);
        setYCoordinate(newY);
        getChessBoard().getPieces()[newX][newY] = this;
    }

    private boolean isValidMove(int newX, int newY) {

        if (PieceColor.WHITE.equals(getPieceColor())) {
            return newX - getXCoordinate() == 1 && (getXCoordinate() < newX && getYCoordinate() == newY);
        } else {
            return getXCoordinate() - newX == 1 && (getXCoordinate() > newX && getYCoordinate() == newY);
        }
        //implement for capture
    }
}
