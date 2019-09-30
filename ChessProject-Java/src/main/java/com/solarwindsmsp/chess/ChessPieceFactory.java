package com.solarwindsmsp.chess;

public class ChessPieceFactory {
    public static ChessPiece MakeChessPiece(PieceType pieceType, PieceColor color) {
        ChessPiece piece = null;
        //TODO: Extend for other chess piece types
        if (color != null && pieceType == PieceType.PAWN) {
            piece = new Pawn(color);
        }
        return piece;
    }
}
