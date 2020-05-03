package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exceptions.UnknownColorException;

public interface Piece {

    boolean isLegalStartingPosition() throws UnknownColorException;

    void move(MovementType movementType, int newX, int newY);

}
