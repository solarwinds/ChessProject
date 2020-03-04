package com.solarwindsmsp.chess;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static int WIDTH = 8;
    public static int HEIGHT = 8;

    private Piece[][] pieces;

    private Map<String, PieceCounter> pieceCounterMap = new HashMap<String, PieceCounter>() {{
        put("p", new PieceCounter(8));
        put("k", new PieceCounter(1));
        put("P", new PieceCounter(8));
        put("K", new PieceCounter(1));
    }};

    public ChessBoard() {
        pieces = new Piece[WIDTH][HEIGHT];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    void add(Piece p, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        PieceCounter pieceCounter = pieceCounterMap.get(p.toString());
        boolean countIsBelowMax = (pieceCounter.getCount() < pieceCounter.getMax());

        if (isLegalBoardPosition(xCoordinate, yCoordinate) && (pieces[xCoordinate][yCoordinate] == null) && countIsBelowMax) {
            p.setXCoordinate(xCoordinate);
            p.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = p;
            pieceCounter.inc();
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
