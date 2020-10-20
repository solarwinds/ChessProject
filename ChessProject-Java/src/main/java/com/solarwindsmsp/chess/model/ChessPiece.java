package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;

public interface ChessPiece {

    void move(MovementType movementType, int newX, int newY);

    boolean isLegalMove(MovementType movementType, int newX, int newY);

    ChessBoard getChessBoard();
}
