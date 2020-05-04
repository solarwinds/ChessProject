package com.solarwindsmsp.chess.validators;

import com.solarwindsmsp.chess.models.Cell;
import com.solarwindsmsp.chess.models.ChessPiece;
import com.solarwindsmsp.chess.enums.MovementType;

import static com.solarwindsmsp.chess.enums.MovementType.MOVE;
import static com.solarwindsmsp.chess.models.ChessBoard.CHESS_BOARD_SIZE;

public class PawnMovementValidator implements MovementValidator {

    public boolean isMovementValid(MovementType movementType, ChessPiece chessPiece, Cell newCell) {
        boolean isMoveValid = false;

        switch (chessPiece.getPieceColor()) {
            case BLACK:
                if (movementType == MOVE) {
                    isMoveValid = isBlackPieceMoveValid(chessPiece, newCell);
                }
                break;
            case WHITE:
                if (movementType == MOVE) {
                    isMoveValid = isWhitePieceMoveValid(chessPiece, newCell);
                }
                break;
            default:
                throw new UnsupportedOperationException("Only PieceColor enum instances allowed");
        }
        // TODO add CAPTURE logic

        return isMoveValid;
    }

    private boolean isWhitePieceMoveValid(final ChessPiece chessPiece, final Cell newCell) {
        boolean isMoveValid = false;
        // Initial state
        Cell oldCell = chessPiece.getCell();
        if (oldCell.getYCoordinate() == 1 &&
                newCell.getYCoordinate() > oldCell.getYCoordinate() && newCell.getYCoordinate() <= oldCell.getYCoordinate() + 2 &&
                oldCell.getXCoordinate() == newCell.getXCoordinate()) {
            isMoveValid = true;
        }

        if (oldCell.getYCoordinate() > 1 &&
                newCell.getYCoordinate() > oldCell.getYCoordinate() && CHESS_BOARD_SIZE - 1 - oldCell.getYCoordinate() > 0 && newCell.getYCoordinate() <= oldCell.getYCoordinate() + 1 &&
                oldCell.getXCoordinate() == newCell.getXCoordinate()) {
            isMoveValid = true;
        }
        return isMoveValid;
    }

    private boolean isBlackPieceMoveValid(final ChessPiece chessPiece, final Cell newCell) {
        boolean isMoveValid = false;

        // Initial state
        Cell oldCell = chessPiece.getCell();
        if (oldCell.getYCoordinate() == CHESS_BOARD_SIZE - 1 &&
                newCell.getYCoordinate() < oldCell.getYCoordinate() && newCell.getYCoordinate() >= oldCell.getYCoordinate() - 2 &&
                oldCell.getXCoordinate() == newCell.getXCoordinate()) {
            isMoveValid = true;
        }

        if (oldCell.getYCoordinate() < CHESS_BOARD_SIZE - 1 &&
                newCell.getYCoordinate() < oldCell.getYCoordinate() && oldCell.getYCoordinate() - 1 > 0 && newCell.getYCoordinate() >= oldCell.getYCoordinate() - 1 &&
                oldCell.getXCoordinate() == newCell.getXCoordinate()) {
            isMoveValid = true;
        }
        return isMoveValid;
    }
}
