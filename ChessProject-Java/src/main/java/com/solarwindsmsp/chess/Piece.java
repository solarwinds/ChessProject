package com.solarwindsmsp.chess;

public abstract class Piece implements IPiece {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void Move(MovementType movementType, int newX, int newY) {

        if (this.chessBoard != null && chessBoard.IsLegalBoardPosition(newX, newY)) {
            switch (movementType) {
                case MOVE:
                    if (allowMoveTo(newX, newY)) {
                        executeMoveForPiece(newX, newY);
                    }
                    break;
                case CAPTURE:
                    if (allowCaptureAt(newX, newY)) {
                        executeMoveForPiece(newX, newY);
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Illegal or unspecified movement type");
            }
        }

    }

    public abstract boolean allowMoveTo(int newX, int newY);

    public abstract boolean allowCaptureAt(int newX, int newY);

    private void executeMoveForPiece(int newX, int newY) {
        getChesssBoard().RemovePieceAt(getXCoordinate(), getYCoordinate());
        getChesssBoard().Add(this, newX, newY, this.pieceColor);
    }


}
