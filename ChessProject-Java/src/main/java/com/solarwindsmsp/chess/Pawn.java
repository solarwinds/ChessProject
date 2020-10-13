package com.solarwindsmsp.chess;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        xCoordinate = -1;
		yCoordinate = -1;
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

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

	public void move(MovementType movementType, int newX, int newY) {
		if (movementType == MovementType.MOVE && isValidMove(newX, newY)) {
			chessBoard.movePiece(this, newX, newY);
		}
	}
    
	private boolean isValidMove(int newX, int newY) {
		return chessBoard.isLegalBoardPosition(newX, newY) && isForwardMove(newX, newY);
	}

	private boolean isValidCapture(int newX, int newY) {
		return chessBoard.isLegalBoardPosition(newX, newY) && isForwardMove(newX, newY) || isDiagonalMove(newX, newY);
	}

	private boolean isForwardMove(int newX, int newY) {
		// WHITE moves from 0 to 7
		if (pieceColor == PieceColor.WHITE) {
			return newX == xCoordinate + 1 && newY == yCoordinate;
		}
		// BLACK moves from 7 to 0
		if (pieceColor == PieceColor.BLACK) {
			return newX == xCoordinate - 1 && newY == yCoordinate;
		}

		return false;
	}

	private boolean isDiagonalMove(int newX, int newY) {
		if (pieceColor == PieceColor.WHITE) {
			return newX == xCoordinate + 1 && (newY == yCoordinate - 1 || newY == yCoordinate + 1);
		}
		if (pieceColor == PieceColor.BLACK) {
			return newX == xCoordinate - 1 && (newY == yCoordinate - 1 || newY == yCoordinate + 1);
		}

		return false;
	}

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}