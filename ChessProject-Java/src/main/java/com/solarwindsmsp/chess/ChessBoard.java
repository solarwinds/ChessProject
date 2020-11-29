package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.models.Piece;

public class ChessBoard {

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;

    private static final int INVALIDATE_COORDINATE = -1;
    private static final ChessBoard INSTANCE = new ChessBoard();

    private Piece[][] pieces;

    private ChessBoard() {
        clearBoard();
    }

    public void clearBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public static ChessBoard getInstance() {
        return INSTANCE;
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate) {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate) || pieces[xCoordinate][yCoordinate] != null) {
            piece.setXCoordinate(INVALIDATE_COORDINATE);
            piece.setYCoordinate(INVALIDATE_COORDINATE);
        } else {
            piece.setXCoordinate(xCoordinate);
            piece.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = piece;
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= MAX_BOARD_WIDTH || yCoordinate >= MAX_BOARD_HEIGHT) {
            return false;
        }

        return xCoordinate >= 0 && yCoordinate >= 0;
    }

}
