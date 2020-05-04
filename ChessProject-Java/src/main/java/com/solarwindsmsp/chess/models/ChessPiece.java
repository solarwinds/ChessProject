package com.solarwindsmsp.chess.models;

import com.solarwindsmsp.chess.enums.MovementType;
import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.enums.PieceType;
import com.solarwindsmsp.chess.validators.MovementValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.solarwindsmsp.chess.validators.MovementValidatorFactory.getValidator;

@Data
@NoArgsConstructor
public class ChessPiece {

    private MovementValidator movementValidator;
    private PieceType pieceType;
    private PieceColor pieceColor;
    private ChessBoard chessBoard;
    private Cell cell;

    public ChessPiece(final PieceType pieceType, final PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.movementValidator = getValidator(pieceType);
    }

    public void move(MovementType movementType, int newX, int newY) {
        Cell newCell = new Cell(newX, newY);
        if (movementValidator.isMovementValid(movementType, this, newCell)) {
            this.cell = newCell;
            // TODO add CAPTURE logic
        }
    }
}
