package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Piece piece, int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate] = piece;
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate>=0 && xCoordinate < MAX_BOARD_WIDTH && yCoordinate >=0 && yCoordinate < MAX_BOARD_HEIGHT;
    }
}
