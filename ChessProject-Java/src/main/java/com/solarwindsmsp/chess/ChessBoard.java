package com.solarwindsmsp.chess;

import java.util.Arrays;
import java.util.Objects;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (isLegalBoardPosition(xCoordinate, yCoordinate)) {
            piece.setChessBoard(this);
            // check if position duplicate or max number of pieces
            if (pieces[xCoordinate][yCoordinate] != null
                    || getNoOfPieces(Pawn.class, pieceColor) >= MAX_BOARD_WIDTH) {
                piece.setXCoordinate(-1);
                piece.setYCoordinate(-1);
            } else {
                piece.setXCoordinate(xCoordinate);
                piece.setYCoordinate(yCoordinate);
                pieces[piece.getXCoordinate()][piece.getYCoordinate()] = piece;
            }
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate > -1 && xCoordinate < MAX_BOARD_WIDTH
                && yCoordinate > -1 && yCoordinate < MAX_BOARD_HEIGHT) {
            return true;
        }
        return false;
    }

    public Piece getPiece(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate];
    }

    private int getNoOfPieces(Class<? extends Piece> pieceType, PieceColor pieceColor) {
        long noOfPawns = Arrays.asList(pieces).stream()
                .flatMap(x -> Arrays.stream(x))
                .filter(Objects::nonNull)
                .filter(p -> p.getClass().equals(pieceType))
                .filter(p -> p.getPieceColor() == pieceColor)
                .count();

        return (int) noOfPawns;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }
}
