package com.solarwindsmsp.chess;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Piece[][] pieces;
    private MovementStrategyFactory strategyFactory;
    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void setStrategyFactory(MovementStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void movePiece(Piece piece, int toxCoordinate, int toYCoordinate){
        if (strategyFactory.getMovementStrategy(piece).canMove(piece, toxCoordinate, toYCoordinate)) {
            pieces[6 - piece.getYCoordinate()][piece.getXCoordinate()] = null;
            pieces[6 - toYCoordinate][toxCoordinate] = piece;

            piece.setXCoordinate(toxCoordinate);
            piece.setYCoordinate(toYCoordinate);
        }
    }

    public void addPiece(Piece pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
            return;
        }

        int row = 6 - yCoordinate;

        if (pieces[row][xCoordinate] != null) {
            return;
        }

        pieces[row][xCoordinate] = pawn;
        pawn.setChessBoard(this);
        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return validatePosition(xCoordinate) && validatePosition(yCoordinate);
    }

    private boolean validatePosition(int xCoordinate) {
        return xCoordinate >= 0 && xCoordinate <= 6;
    }
}
