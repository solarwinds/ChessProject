package com.solarwindsmsp.chess;

public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    public Pawn(ChessBoard chessBoard, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        super(chessBoard, xCoordinate, yCoordinate, pieceColor);
    }

    public void move(MovementType movementType, int newX, int newY) {

        Piece[][] pieces = getChessBoard().getPieces();

        if(movementType.equals(MovementType.MOVE)) {

            if (((getPieceColor() == PieceColor.WHITE && newY == getyCoordinate() && newX == getxCoordinate() + 1) ||
                    (getPieceColor() == PieceColor.BLACK && newY == getyCoordinate() && newX == getxCoordinate() - 1)) &&
                    getChessBoard().isFreeBoardPosition(newX, newY)) {

                getChessBoard().removePiece(getxCoordinate(), getyCoordinate());
                setxCoordinate(newX);
                pieces[newX][newY] = this;
                getChessBoard().setPieces(pieces);
            }

        }
    }

    public void capture(MovementType movementType, int newX, int newY) {

        Piece[][] pieces = getChessBoard().getPieces();

        if(movementType.equals(MovementType.CAPTURE)) {

            if((newY == getyCoordinate() + 1 || newY == getyCoordinate() - 1) &&
                    newX == getxCoordinate() + 1 &&
                    !getChessBoard().isFreeBoardPosition(newX, newY)) {

                getChessBoard().removePiece(newX, newY);
                getChessBoard().removePiece(getxCoordinate(), getyCoordinate());
                setxCoordinate(newX);
                setyCoordinate(newY);
                pieces[newX][newY] = this;
                getChessBoard().setPieces(pieces);
            }
        }
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, getxCoordinate(),
                getyCoordinate(), getPieceColor());
    }
}
