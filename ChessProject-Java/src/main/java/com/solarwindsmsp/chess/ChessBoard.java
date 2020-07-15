package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if(isLegalBoardPosition(xCoordinate, yCoordinate) &&
                !existsAnotherPieceWithSameColor(xCoordinate,yCoordinate,pieceColor)) {
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pawn.setChessBoard(this);
            pieces[xCoordinate][yCoordinate] = pawn;
        }
        else {
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        }
    }

    private boolean existsAnotherPieceWithSameColor(int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        return pieces[xCoordinate][yCoordinate] != null && pieces[xCoordinate][yCoordinate].getPieceColor().equals(pieceColor);
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return isLegalCoordinate(xCoordinate) && isLegalCoordinate(yCoordinate);
    }

    private boolean isLegalCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate <= 6;
    }
}
