package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;
    private int numberOfBlackPawns;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
        numberOfBlackPawns = 0;
    }

    public boolean addPiece(Piece piece) {
        int xCoordinate = piece.getXCoordinate();
        int yCoordinate = piece.getYCoordinate();
        if(pieces[xCoordinate][yCoordinate] == null) {
            if(piece instanceof Pawn) {
                if (numberOfBlackPawns < 8) {
                    pieces[xCoordinate][yCoordinate] = piece;
                    numberOfBlackPawns++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean movePiece(Piece piece, int newX, int newY) {
        if(piece != null && piece == pieces[piece.getXCoordinate()][piece.getYCoordinate()]) {
            if(pieces[newX][newY] == null) {
                pieces[newX][newY] = piece;
                pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null;
                return true;
            }
        }
        return false;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if( xCoordinate > MAX_BOARD_WIDTH || xCoordinate < 0 ) {
            return false;
        }
        if( yCoordinate > MAX_BOARD_HEIGHT || yCoordinate < 0 ) {
            return false;
        }
        return true;
    }
}
