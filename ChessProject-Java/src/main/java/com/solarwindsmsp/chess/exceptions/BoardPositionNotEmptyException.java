package com.solarwindsmsp.chess.exceptions;

import com.solarwindsmsp.chess.pieces.Piece;

public class BoardPositionNotEmptyException extends Exception  {

    public BoardPositionNotEmptyException(Piece piece, int newX, int newY, Piece currentPiece) {
        super(String.format("Cannot add piece %s to (%d, %d) because position is occupied by current piece: %s", piece, newX, newY, currentPiece));
    }

}
