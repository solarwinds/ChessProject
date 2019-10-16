package com.solarwindsmsp.chess.piece;

public class ChessPieceFactory {

    public ChessPieceFactory() {
        //Static Factory Class
    }

    static ChessPiece create(PieceName pieceName, PieceColor pieceColor) {
        switch (pieceName) {
            case PAWN:
                return new Pawn(pieceColor);
            default:
                throw new IllegalArgumentException("Unsupported ChessPiece: " + pieceName);
        }
    }
}