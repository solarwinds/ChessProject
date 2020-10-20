package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.pieces.Pawn;
import com.solarwindsmsp.chess.pieces.Piece;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void add(Piece piece, int xCoordinate, int yCoordinate) throws IllegalBoardPlacementException {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
            throw new IllegalBoardPlacementException("Ilegal board position");
        }

        if (!piece.IsLegalPiecePosition(xCoordinate, yCoordinate)) {
            throw new IllegalBoardPlacementException("Ilegal piece position");
        }

        if (!isPositionAvailable(xCoordinate, yCoordinate)) {
            throw new IllegalBoardPlacementException("Position not available");
        }

        placePiece(piece, xCoordinate, yCoordinate);
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return isValidRow(xCoordinate) && isValidColumn(yCoordinate);
    }

    private boolean isValidRow(int xCoordinate) {
        return (xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH);
    }

    private boolean isValidColumn(int yCoordinate) {
        return (yCoordinate >= 0 && yCoordinate < MAX_BOARD_WIDTH);
    }

    public boolean isPositionAvailable(int xCoordinate, int yCoordinate) {
        return (pieces[xCoordinate][yCoordinate] == null);
    }

    public void placePiece(Piece piece, int xCoordinate, int yCoordinate) {
        piece.setChessBoard(this);
        piece.setXCoordinate(xCoordinate);
        piece.setYCoordinate(yCoordinate);
        pieces[xCoordinate][yCoordinate] = piece;
    }

    public Piece getPiece(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate];
    }

    private void removePiece(int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate] = null;
    }
}
