package com.solarwindsmsp.chess.piece;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    protected PieceName getName() {
        return PieceName.PAWN;
    }

    @Override
    protected boolean isValidMoveForPiece(int newXCoordinate, int newYCoordinate) {
        switch (this.getPieceColor()) {
            case WHITE:
                return newYCoordinate - this.getYCoordinate() == 1;
            case BLACK:
                return this.getYCoordinate() - newYCoordinate == 1;
            default:
                throw new IllegalArgumentException("Unknown Piece Colour: " + getPieceColor());
        }
    }
}