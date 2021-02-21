package com.solarwindsmsp.chess;

public class Pawn extends Piece{

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    public boolean move(int newX, int newY) {
        if(getChessBoard() == null) {
            return false;
        }

        if(getXCoordinate() == newX && getYCoordinate() - newY == 1) {
            if(getChessBoard().isLegalBoardPosition(newX, newY)) {
                getChessBoard().movePiece(this, newX, newY);
                setYCoordinate(newY);
                return true;
            }
        }
        return false;
    }
}
