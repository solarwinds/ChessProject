package com.solarwindsmsp.chess.exception;

import com.solarwindsmsp.chess.board.BoardInventory;
import com.solarwindsmsp.chess.piece.Piece;

public class MaxNumberOfPiecesReachedException extends RuntimeException {
    public MaxNumberOfPiecesReachedException(Piece piece) {
        super("Max number of "+ BoardInventory.maxAllowed.get(piece.getClass())+" "+piece.getClass()
                +" of colour "+piece.getPieceColor()+" has been reached");
    }
}
