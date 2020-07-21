package com.solarwindsmsp.chess;


public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {

        if((pieceColor.equals(PieceColor.WHITE) && (xCoordinate != 1 || yCoordinate < 0 || yCoordinate > 7)) ||
                (pieceColor.equals(PieceColor.BLACK) && (xCoordinate != 6 || yCoordinate < 0 || yCoordinate > 7)) ||
                !isFreeBoardPosition(xCoordinate, yCoordinate)) {
                xCoordinate = -1;
                yCoordinate = -1;
        }

        pawn.setxCoordinate(xCoordinate);
        pawn.setyCoordinate(yCoordinate);

        if(xCoordinate != -1 && yCoordinate != -1) {
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        pawn.setChessBoard(this);
    }

    public void removePiece(int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate] = null;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if(xCoordinate < 0 || xCoordinate >= MAX_BOARD_WIDTH || yCoordinate < 0 || yCoordinate >= MAX_BOARD_HEIGHT) {
           return false;
        }

        return true;
    }

    public boolean isFreeBoardPosition(int xCoordinate, int yCoordinate) {
        if(pieces[xCoordinate][yCoordinate] != null) {
            return false;
        }

        return true;
    }
}
