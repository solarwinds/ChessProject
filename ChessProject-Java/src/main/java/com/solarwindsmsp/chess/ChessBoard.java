package com.solarwindsmsp.chess;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private final Pawn[][] pieces;
    private final Map<PieceColor, Integer> pawnsByColor = new HashMap<>();

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
        pawnsByColor.put(PieceColor.WHITE, 0);
        pawnsByColor.put(PieceColor.BLACK, 0);
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!this.isLegalBoardPosition(xCoordinate, yCoordinate)) {
            throw new IllegalArgumentException("Illegal board position");
        }

        if (pawnsByColor.get(pieceColor) == 8) {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
            return;
        }

        if (pieces[xCoordinate][yCoordinate] == null) {
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = pawn;
            pawnsByColor.put(pieceColor, pawnsByColor.get(pieceColor) + 1);
        } else {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        }
    }

    public void removePiece(int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate] = null;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH &&
                yCoordinate >= 0 && yCoordinate < MAX_BOARD_HEIGHT;
    }

    public boolean piecePresent(PieceColor color, int x, int y) {
        return pieces[x][y] != null && pieces[x][y].getPieceColor().equals(color);
    }
}
