package com.solarwindsmsp.chess;

public class ChessBoard {

    public static final int OUT_OF_BOUNDS_BORDER = -1;
    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;
    private static ChessBoard INSTANCE;
    private Piece[][] pieces;

    private ChessBoard() {
        createBoard();
    }

    public static ChessBoard getInstance() {
        return (INSTANCE == null) ? new ChessBoard() : INSTANCE;
    }

    public void createBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate) {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate) || pieces[xCoordinate][yCoordinate] != null) {
            piece.setXCoordinate(OUT_OF_BOUNDS_BORDER);
            piece.setYCoordinate(OUT_OF_BOUNDS_BORDER);
        } else {
            piece.setXCoordinate(xCoordinate);
            piece.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = piece;
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return (xCoordinate >= 0) && (xCoordinate < MAX_BOARD_WIDTH) && (yCoordinate >= 0) && (yCoordinate < MAX_BOARD_HEIGHT);
    }

}
