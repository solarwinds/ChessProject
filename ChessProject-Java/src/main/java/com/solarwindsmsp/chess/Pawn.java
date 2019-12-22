package com.solarwindsmsp.chess;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChessBoard() {
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

    public void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
        ChessBoard board = this.getChessBoard();
        if (board.IsLegalBoardPosition(newX, newY)) {
            if (movementType == MovementType.MOVE) {
                if (canMove(newX, newY)) {
                    board.ChangePosition(this, newX, newY);
                    this.setXCoordinate(newX);
                    this.setYCoordinate(newY);
                }
            } else {
                // TODO: MovementType.CAPTURE
            }
        }
    }

    private boolean canMove(int newX, int newY) {
        int starterColumn = (this.getPieceColor() == PieceColor.BLACK) ? 6 : 1;
        int direction = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;
        int currentX = getXCoordinate();
        int currentY = getYCoordinate();
        if (newX != currentX) {
            return false;
        } else if (currentY == starterColumn) {
            return newY == currentY + direction || newY == currentY + (direction * 2);
        } else {
            return newY == currentY + direction;
        }
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
