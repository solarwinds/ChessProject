package com.solarwindsmsp.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private List<Figure> pieces;
    private int noOfpawns;

    public ChessBoard() {
        pieces = new ArrayList<>();
        noOfpawns = 0;
    }

    public void addPiece(Figure pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (noOfpawns < MAX_BOARD_WIDTH + 1) {
            if (!isDuplicate(pawn, xCoordinate, yCoordinate, pieceColor)) {
                addPawn(pawn, xCoordinate, yCoordinate, pieceColor);
                noOfpawns++;
            }
        }
    }

    public void addPawn(Figure pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        pawn.setChessBoard(this);
        pawn.setPieceColor(pieceColor);
        pieces.add(pawn);
    }

    public boolean isDuplicate(Figure figure, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        return this.pieces.stream()
                .filter(p -> p.getXCoordinate() == xCoordinate && p.getYCoordinate() == yCoordinate)
                .count() > 0 ? true: false;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return yCoordinate >=0 && yCoordinate <=7 && xCoordinate >=0 && xCoordinate <= 7;
    }
}
