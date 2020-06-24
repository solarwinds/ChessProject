package com.solarwindsmsp.chess.piece;

public class Pawn extends Piece{

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return endY - startY == 1;
    }
}
