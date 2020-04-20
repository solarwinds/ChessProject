package com.solarwindsmsp.chess.exceptions;

import com.solarwindsmsp.chess.pieces.Piece;

public class MaximumNumberOfPiecesReached extends Exception {

    public MaximumNumberOfPiecesReached(Piece piece, int newX, int newY, int maxNumber) {
        super(String.format("Cannot add piece %s to (%d, %d) because maximum number %d was reached", piece, newX, newY, maxNumber));
    }

}
