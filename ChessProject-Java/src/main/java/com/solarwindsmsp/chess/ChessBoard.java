package com.solarwindsmsp.chess;

public class ChessBoard {

    public final static int MAX_BOARD_WIDTH = 7;
    public final static int MAX_BOARD_HEIGHT = 7;

    private IPiece[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(IPiece newPieceToAdd, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if(IsLegalBoardPosition(xCoordinate, yCoordinate)) {
            pieces[xCoordinate][yCoordinate] = newPieceToAdd;
            newPieceToAdd.setXCoordinate(xCoordinate);
            newPieceToAdd.setYCoordinate(yCoordinate);
            newPieceToAdd.setChessBoard(this);
        }
        else {
            newPieceToAdd.setXCoordinate(-1);
            newPieceToAdd.setYCoordinate(-1);
        }
    }

    public void RemovePieceAt(int xCoordinate, int yCoordinate) {
        if(IsLegalBoardPosition(xCoordinate, yCoordinate)) {
            pieces[xCoordinate][yCoordinate] = null;
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >=0 &&
                xCoordinate < MAX_BOARD_WIDTH &&
                yCoordinate >= 0 &&
                yCoordinate < MAX_BOARD_HEIGHT;
    }

}
