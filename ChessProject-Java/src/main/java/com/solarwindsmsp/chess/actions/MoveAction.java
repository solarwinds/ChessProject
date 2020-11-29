package com.solarwindsmsp.chess.actions;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;

public interface MoveAction {

    default void move(MovementType movementType, int newX, int newY) {
        if (ChessBoard.getInstance().isLegalBoardPosition(newX, newY)
                && isLegalMoveAction(movementType, newX, newY)) {
            updatePosition(newX, newY);
        }
    }

    default boolean isLegalMoveAction(MovementType movementType, int newX, int newY) {
        switch (movementType) {
            case MOVE:
                return isLegalMovePosition(newX, newY);
            case CAPTURE:
                return isLegalCapturePosition(newX, newY);
        }

        return false;
    }

    boolean isLegalMovePosition(int newX, int newY);

    boolean isLegalCapturePosition(int newX, int newY);

    void updatePosition(int newX, int newY);
}
