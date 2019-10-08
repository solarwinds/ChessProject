package com.solarwindsmsp.chess;

public class Pawn extends Piece {

//    private ChessBoard chessBoard;
//    private int xCoordinate;
//    private int yCoordinate;
//    private PieceColour pieceColor;
	private Cell cell;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

//    public ChessBoard getChesssBoard() {
//        return chessBoard;
//    }

//    public void setChessBoard(ChessBoard chessBoard) {
//        this.chessBoard = chessBoard;
//    }

//    public int getXCoordinate() {
//        return xCoordinate;
//    }

//    public void setXCoordinate(int value) {
//        this.xCoordinate = value;
//    }

//    public int getYCoordinate() {
//        return yCoordinate;
//    }

//    public void setYCoordinate(int value) {
//        this.yCoordinate = value;
//    }

    public PieceColor getPieceColor() {
        return colour;
    }

//    private void setPieceColor(PieceColor value) {
//        pieceColor = value;
//    }

    public void Move(MovementType movementType, int newX, int newY) {
        throw new UnsupportedOperationException("Need to implement Pawn.Move()") ;
    }



	@Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}
	
    public Cell getCell() {
		return cell;
	}

	public void setCell(Cell aCell) {
		this.cell = aCell;
	}
}
