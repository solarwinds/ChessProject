package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.model.ChessPiece;
import com.solarwindsmsp.chess.model.ChessPieceType;
import com.solarwindsmsp.chess.model.Pawn;

public class ChessPieceFactory {

    public ChessPiece getChessPiece(ChessPieceType chessPieceType, int x, int y, PieceColor color){
        if(chessPieceType == null){
            return null;
        }
        switch (chessPieceType) {
            case PAWN:
                return new Pawn(color, x, y);
            default:
                throw new UnsupportedOperationException("");
        }
    }
}
