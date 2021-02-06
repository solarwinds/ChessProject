package com.solarwindsmsp.chess;

public class PieceFactory {
    private static PieceFactory INSTANCE;

    private PieceFactory() { }

    public static PieceFactory getInstance() {
        return (INSTANCE == null) ? new PieceFactory() : INSTANCE;
    }

    public Piece createPiece(Piece.TypePiece typePiece, PieceColor pieceColor){
        if (typePiece == Piece.TypePiece.PAWN) {
            return new Pawn(pieceColor);
        }
        throw new UnsupportedOperationException("Type " + typePiece + " not yet supported!");
    }
}
