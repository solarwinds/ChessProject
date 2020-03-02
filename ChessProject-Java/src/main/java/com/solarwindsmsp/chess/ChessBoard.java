package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    private Pawn[][] pieces;

    private int[] pawnCount = new int[2];

    public ChessBoard() {
        pieces = new Pawn[WIDTH][HEIGHT];
    }

    public Pawn[][] getPieces() {
        return pieces;
    }

    void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (IsLegalBoardPosition(xCoordinate, yCoordinate) && pieces[xCoordinate][yCoordinate] == null && pawnCount[pieceColor.getCode()] < 8) {
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = pawn;
            pawnCount[pieceColor.getCode()]++;
        } else {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return 0 <= xCoordinate && xCoordinate < 8 && 0 <= yCoordinate && yCoordinate < 8;
    }
}
