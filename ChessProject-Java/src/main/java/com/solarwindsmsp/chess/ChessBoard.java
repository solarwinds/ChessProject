package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate) throws IllegalBoardCoordinatesException {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
            throw new IllegalBoardCoordinatesException("Invalid coordinates.", xCoordinate, yCoordinate);
        }
        setProperties(pawn, xCoordinate, yCoordinate);
        pieces[xCoordinate][yCoordinate] = pawn;
    }

    public boolean removePiece(int xCoordinate, int yCoordinate) {
        if (pieces[xCoordinate][yCoordinate] == null) {
            return false;
        }
        pieces[xCoordinate][yCoordinate] = null;
        return true;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH &&
                yCoordinate >= 0 && yCoordinate < MAX_BOARD_HEIGHT;
    }

    private void setProperties(Pawn pawn, int xCoordinate, int yCoordinate) {
        pawn.setChessBoard(this);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
    }

}
