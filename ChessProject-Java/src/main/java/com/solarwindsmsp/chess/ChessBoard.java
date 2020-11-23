package com.solarwindsmsp.chess;

import java.util.Map;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private final static Map<Class<? extends Piece>, Integer> MAX_ALLOWED = Map.of(Pawn.class, 8);

    private final Piece[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!this.isLegalBoardPosition(xCoordinate, yCoordinate)) {
            this.reject(piece);
            return;
        }

        if (this.count(piece.getClass(), pieceColor) == MAX_ALLOWED.get(piece.getClass())) {
            this.reject(piece);
            return;
        }

        if (pieces[xCoordinate][yCoordinate] != null) {
            this.reject(piece);
            return;
        }

        piece.setXCoordinate(xCoordinate);
        piece.setYCoordinate(yCoordinate);
        pieces[xCoordinate][yCoordinate] = piece;
    }

    private void reject(Piece piece) {
        piece.setXCoordinate(-1);
        piece.setYCoordinate(-1);
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

    private int count(Class<? extends Piece> clazz, PieceColor pieceColor) {
        int res = 0;
        for (int i = 0; i < MAX_BOARD_WIDTH; i++) {
            for (int j = 0; j < MAX_BOARD_HEIGHT; j++) {
                if (pieces[i][j] != null &&
                        pieces[i][j].getPieceColor().equals(pieceColor) &&
                        pieces[i][j].getClass().equals(clazz)) {
                    res++;
                }
            }
        }

        return res;
    }
}
