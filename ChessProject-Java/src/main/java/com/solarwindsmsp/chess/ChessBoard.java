package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void add(Piece piece, int xCoordinate, int yCoordinate) {
        if(isLegalBoardPosition(xCoordinate,yCoordinate)) {
            piece.setChessBoard(this);
            piece.setXCoordinate(xCoordinate);
            piece.setYCoordinate(yCoordinate);
            pieces[xCoordinate][yCoordinate] = piece;
        }else{
            piece.setXCoordinate(-1);
            piece.setYCoordinate(-1);
        }
    }


    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate>=0 && xCoordinate < MAX_BOARD_WIDTH && yCoordinate >=0 && yCoordinate < MAX_BOARD_HEIGHT;
    }

    public void move(int xOld, int yOld, int newX, int newY) {
        pieces[newX][newY] = pieces[xOld][yOld];
        pieces[xOld][yOld] = null;
    }
}
