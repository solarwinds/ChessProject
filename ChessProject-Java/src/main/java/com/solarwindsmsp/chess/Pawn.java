package com.solarwindsmsp.chess;

public class Pawn {

	private ChessBoard chessBoard;
	private int xCoordinate;
	private int yCoordinate;
	private PieceColor pieceColor;

	public Pawn(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
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

	/* Changes done : 
	 * Changed return type of setter method to public instead of private
	 * */
	
	public void setPieceColor(PieceColor value) {
		pieceColor = value;
	}

	/* Changes done :
	 * Implemented method Move()
	 * */
	
	public void Move(MovementType movementType, int newX, int newY) {
		if(movementType.equals(MovementType.MOVE)){
			if(pieceColor.equals(PieceColor.BLACK)){
				if((xCoordinate-newX)>1){
					setXCoordinate(xCoordinate);
					setYCoordinate(yCoordinate);
				}
				else if(xCoordinate>newX){
					xCoordinate=newX;
					yCoordinate=newY;
				}
				else if(xCoordinate==newX){
					if((newY-1 == yCoordinate) || (newY+1 == yCoordinate)){
						xCoordinate=newX;
						yCoordinate=newY;
					}
				}
				
			}
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
