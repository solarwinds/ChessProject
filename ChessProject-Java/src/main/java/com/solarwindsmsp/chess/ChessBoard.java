package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private static int MAX_PAWNS_ALLOWED = MAX_BOARD_WIDTH;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!IsLegalBoardPosition(xCoordinate, yCoordinate) || numberOfPawns() >= MAX_PAWNS_ALLOWED) {
            xCoordinate = yCoordinate = -1;
        } else {
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        pawn.setChessBoard(this);
        pawn.setPieceColor(pieceColor);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 0 || xCoordinate >= MAX_BOARD_WIDTH) {
            return false;
        }
        if (yCoordinate < 0 || yCoordinate >= MAX_BOARD_HEIGHT) {
            return false;
        }
        return true;
    }

    public void ChangePosition(Pawn pawn, int newX, int newY) {
        pieces[pawn.getXCoordinate()][pawn.getYCoordinate()] = null;
        pieces[newX][newY] = null;
    }

    private int numberOfPawns() {
        int nPawns = 0;
        for (Pawn[] rows: pieces) {
            for (Pawn pawn: rows) {
                if (pawn != null) {
                    nPawns++;
                }
            }
        }
        return nPawns;
    }
}
