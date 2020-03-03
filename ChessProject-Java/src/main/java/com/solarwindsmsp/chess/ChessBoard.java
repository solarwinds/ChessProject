package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    private Piece[][] pieces;

    private int[] pawnCount = new int[2];

    public ChessBoard() {
        pieces = new Pawn[WIDTH][HEIGHT];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    void add(Piece p, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (isLegalBoardPosition(xCoordinate, yCoordinate) && pieces[xCoordinate][yCoordinate] == null && pawnCount[pieceColor.getCode()] < 8) {
            p.setXCoordinate(xCoordinate);
            p.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = p;
            pawnCount[pieceColor.getCode()]++;
        } else {
            p.setXCoordinate(-1);
            p.setYCoordinate(-1);
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return 0 <= xCoordinate && xCoordinate < 8 && 0 <= yCoordinate && yCoordinate < 8;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            sb.append("\n");
            sb.append("---------------------------------\n");

            for (int column = 0; column < 8; column++) {
                String square = this.pieces[column][7 - row] != null ? this.pieces[column][7 - row].toString() : " ";
                sb.append("| " + square + " ");
            }
            sb.append("|");
        }
        sb.append("\n");
        sb.append("---------------------------------\n");

        return sb.toString();
    }
}
