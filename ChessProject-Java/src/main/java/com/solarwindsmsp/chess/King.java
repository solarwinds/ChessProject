package com.solarwindsmsp.chess;

public class King extends Piece {

    public King(PieceColor pieceColor) {
        super(pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {
        if (movementType == MovementType.CAPTURE) {
            throw new UnsupportedOperationException("Need to implement King.Move() for CAPTURE");
        }

        if (!chessBoard.isLegalBoardPosition(newX, newY)) {
            return;
        }

        if (isLegalMove(newX, newY)) {
            chessBoard.getPieces()[newX][newY] = this;
            chessBoard.getPieces()[xCoordinate][yCoordinate] = null;
            xCoordinate = newX;
            yCoordinate = newY;
        }
    }

    private boolean isLegalMove(double x, double y) {
        int d = (int) (Math.pow((x - xCoordinate), 2) + Math.pow((y - yCoordinate), 2));
        return (d == 1 || d == 2);
    }

    @Override
    public String toString() {
        return pieceColor == PieceColor.BLACK ? "k" : "K";
    }

}
//todo test for move to occupied square
//todo abstract canMove() method
