package com.solarwindsmsp.chess.piece;

public class ChessPieceManager {

    private ChessPieceManager() {
        //Utility Class
    }

    static boolean performAction(ChessPiece chessPiece, MovementType movementType, int xCoordinate, int yCoordinate) {
        switch (movementType) {
            case MOVE:
                if (chessPiece.isValidMoveForPiece(xCoordinate, yCoordinate)) {
                    return chessPiece.getChessBoard().move(chessPiece.getXCoordinate(), chessPiece.getYCoordinate(), xCoordinate, yCoordinate);
                } else {
                    return false;
                }
            case CAPTURE:
                throw new UnsupportedOperationException("Capture functionality not currently implemented");
            default:
                throw new UnsupportedOperationException("Unknown Movement Type: " + movementType);
        }
    }
}