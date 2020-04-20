package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.MovementType;

import java.util.stream.IntStream;

/**
 * Pawn class
 */
public class Pawn extends Piece {

    public final static int MAX_PIECES = 8;

    private final static int WHITE_PAWN_ROW = 1;
    private final static int BLACK_PAWN_ROW = 6;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public int getMaxNumberOfPieces() {
        return MAX_PIECES;
    }

    @Override
    boolean isValidMoveForPiece(MovementType movementType, int newX, int newY) {
        switch (movementType) {
            case MOVE:
                if (yCoordinate == WHITE_PAWN_ROW && pieceColor == PieceColor.WHITE) {
                    // white pawn is initial position, it can move one or two spaces forward
                    return (newY - yCoordinate == 1 || newY - yCoordinate == 2) && newX == xCoordinate;
                } else if (yCoordinate == BLACK_PAWN_ROW && pieceColor == PieceColor.BLACK) {
                    // black pawn is initial position, it can move one or two spaces forward
                    return (yCoordinate - newY == 1 || yCoordinate - newY == 2) && newX == xCoordinate;
                } else if (pieceColor == PieceColor.WHITE) {
                    // white pawn is not in initial position, it can only move one space forward
                    return newY - yCoordinate == 1 && newX == xCoordinate;
                } else { // pieceColor == PieceColor.BLACK
                    // black pawn is not in initial position, it can only move one space forward
                    return yCoordinate - newY == 1 && newX == xCoordinate;
                }
            case CAPTURE:
                throw new UnsupportedOperationException("Need to implement Pawn.Capture()") ;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported move: %s", movementType));
        }
    }

    public boolean isValidMoveForChessBoard(MovementType movementType, int newX, int newY) {
        switch (movementType) {
            case MOVE:
                // pawn path is not occupied by other pieces
                if (pieceColor == PieceColor.WHITE) {
                    return IntStream.rangeClosed(yCoordinate + 1, newY).allMatch(y -> chessBoard.isEmptyBoardPosition(xCoordinate, y));
                } else { // pieceColor == PieceColor.BLACK
                    return IntStream.rangeClosed(newY, yCoordinate - 1).allMatch(y -> chessBoard.isEmptyBoardPosition(xCoordinate, y));
                }
            case CAPTURE:
                throw new UnsupportedOperationException("Need to implement Pawn.Capture()");
            default:
                throw new UnsupportedOperationException(String.format("Unsupported move: %s", movementType));
        }
    }
}
