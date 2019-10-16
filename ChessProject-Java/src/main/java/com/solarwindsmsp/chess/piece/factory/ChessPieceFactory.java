package com.solarwindsmsp.chess.piece.factory;

import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.piece.PieceName;

public class ChessPieceFactory {

    public ChessPieceFactory() {
        //Static Factory Class
    }

    public static ChessPiece create(PieceName pieceName, PieceColor pieceColor) {
        switch (pieceName) {
            case PAWN:
                return new Pawn(pieceColor);
            default:
                throw new IllegalArgumentException("Unsupported ChessPiece: " + pieceName);
        }
    }
}