package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.game.MovementType;
import com.solarwindsmsp.chess.game.PieceColor;
import com.solarwindsmsp.chess.game.PieceType;

import static com.solarwindsmsp.chess.constants.Constants.VALID_BLACK_PAWN_STARTING_POSITION_INDEX;
import static com.solarwindsmsp.chess.constants.Constants.VALID_WHITE_PAWN_STARTING_POSITION_INDEX;
import static com.solarwindsmsp.chess.game.PieceColor.WHITE;
import static com.solarwindsmsp.chess.utils.GameUtils.isLegalBoardPosition;
import static com.solarwindsmsp.chess.utils.GameUtils.positionIsEmpty;


/**
 * Class to define a Pawn chess piece
 */
public class Pawn extends ChessPiece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        pieceType = PieceType.PAWN;
    }

    @Override
    public void move(MovementType movementType, int newXCoordinate, int newYCoordinate) {
        if (isLegalBoardPosition(newXCoordinate, newYCoordinate)) {
            if (MovementType.MOVE.equals(movementType)) {
                if (positionIsEmpty(chessBoard, newXCoordinate, newYCoordinate)) {
                    switch (pieceColor) {
                        case BLACK:
                            if (yCoordinate == newYCoordinate && newXCoordinate == this.xCoordinate - 1) {
                                updatePawnCoordinatesAndPawnPositionOnChessBoard(newXCoordinate);
                            }

                        case WHITE:
                            if (yCoordinate == newYCoordinate && newXCoordinate == this.xCoordinate + 1) {
                                updatePawnCoordinatesAndPawnPositionOnChessBoard(newXCoordinate);
                            }
                    }
                }

            } else if (MovementType.CAPTURE.equals(movementType)) {
                throw new UnsupportedOperationException("Need to implement CAPTURE move");
            }
        }
    }

    @Override
    public boolean startsInLegalPosition(int xCoordinate) {
        if (WHITE.equals(this.pieceColor)) {
            return xCoordinate == VALID_WHITE_PAWN_STARTING_POSITION_INDEX;
        }

        return xCoordinate == VALID_BLACK_PAWN_STARTING_POSITION_INDEX;
    }

    private void updatePawnCoordinatesAndPawnPositionOnChessBoard(int newXCoordinate) {
        this.chessBoard.updatePieceLocation(this, xCoordinate, yCoordinate);
        this.xCoordinate = newXCoordinate;
    }
}
