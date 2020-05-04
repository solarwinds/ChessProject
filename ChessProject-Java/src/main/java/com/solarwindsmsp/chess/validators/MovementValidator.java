package com.solarwindsmsp.chess.validators;

import com.solarwindsmsp.chess.models.Cell;
import com.solarwindsmsp.chess.models.ChessPiece;
import com.solarwindsmsp.chess.enums.MovementType;

public interface MovementValidator {

    boolean isMovementValid(MovementType movementType, ChessPiece chessPiece, Cell newCell);

}
