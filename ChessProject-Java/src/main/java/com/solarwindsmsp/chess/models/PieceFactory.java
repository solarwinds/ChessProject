package com.solarwindsmsp.chess.models;

import com.solarwindsmsp.chess.PieceColor;

import static com.solarwindsmsp.chess.models.Piece.*;

public class PieceFactory {

    public static Piece createPiece(Type type, PieceColor pieceColor) {
        if (type == Type.Pawn) {
            return new Pawn(pieceColor);
        }

        throw new UnsupportedOperationException("Type " + type + " not supported!");
    }

}
