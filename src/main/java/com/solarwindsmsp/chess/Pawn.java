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

    void setPieceColor(PieceColor value) {
         pieceColor = value;
    }

    public MovementType move(MovementType movementType, int newX, int newY) { 
    	ChessBoard board = this.getChessBoard();
    	if (board.isLegalBoardPosition(newX, newY)) {
    		if (movementType == MovementType.MOVE) {
    			if (!board.isOccupied(newX, newY) && isValidMove(newX, newY)) {
    				board.ChangePosition(this, newX, newY);
    				setXCoordinate(newX);
    				setYCoordinate(newY);
    			} else {
    	            return movementType;
    			}
    		} else {
    			// Capture Movement
    		  }
    	}
    	return movementType;
    }

    protected boolean isValidMove(int newX, int newY) {
        int starterColumnIndex = (this.getPieceColor() == PieceColor.BLACK) ? 6 : 1;
        int direction = (this.getPieceColor() == PieceColor.BLACK) ? -1 : 1;
        int currentX = getXCoordinate();
        int currentY = getYCoordinate();
        if (newX != currentX) {
            return false;
        } else if (currentY == starterColumnIndex) {
            return newY == currentY + direction || newY == currentY + (direction * 2);
        } else {
            return newY == currentY + direction;
        }
    }
}

