package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;

public class TestHelper {

    static void assertCoordinates(Piece p, int x, int y) {
        assertEquals(x, p.getXCoordinate());
        assertEquals(y, p.getYCoordinate());
    }

    static void assertThatPiecesArePresentAtExpectedCoordinates(ChessBoard chessBoard, int rank, int upToFile) {
        for (int file = 0; file < upToFile; file++) {
            assertEquals(file, chessBoard.getPieces()[rank][file].getYCoordinate());
            assertEquals(rank, chessBoard.getPieces()[rank][file].getXCoordinate());
        }
    }

    static void AddFullRankOfPawns(ChessBoard chessBoard, PieceColor colour, int rank) {
        for (int file = 0; file < ChessBoard.WIDTH; file++) {
            addPiece(chessBoard, new Pawn(colour), rank, file);
        }
    }

    static void addPiece(ChessBoard chessBoard, Piece p, int rank, int file) {
        chessBoard.add(p, rank, file, p.getPieceColor());
    }

}
