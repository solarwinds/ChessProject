package com.solarwindsmsp.chess.exception;

import com.solarwindsmsp.chess.movement.MovementType;
import com.solarwindsmsp.chess.piece.Piece;

public class IllegalMoveException extends RuntimeException {
    public IllegalMoveException(Piece piece, MovementType movementType, int x, int y) {
        super(String.format("Cannot move piece %s to coordinates x:%s, y:%s with movement type %s",
                piece, x, y, movementType));
    }
}
