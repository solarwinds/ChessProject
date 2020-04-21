package com.solarwindsmsp.chess.exceptions;

import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.pieces.Piece;

/**
 * Invalid Move Exception
 */
public class InvalidMoveException extends Exception {

    public InvalidMoveException(Piece piece, int newX, int newY, MovementType movementType) {
        super(String.format("Cannot move piece %s to (%d, %d), movement type: %s", piece, newX, newY, movementType));
    }
}
