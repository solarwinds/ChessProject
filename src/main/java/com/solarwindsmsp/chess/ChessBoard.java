package com.solarwindsmsp.chess;
public class ChessBoard {
	public static int MAX_BOARD_WIDTH = 7;
	public static int MAX_BOARD_HEIGHT = 7;
	public static int PAWNS = 8;     //Added 
	private Pawn[][] pieces;

	public ChessBoard() {
		pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
	}
	public Pawn addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
		if (pawn.getChessBoard() != null) {
			 return pawn;
		}
		if (!isLegalBoardPosition(xCoordinate, yCoordinate) || isOccupied(xCoordinate, yCoordinate)
				|| ((pawn instanceof Pawn)) && numberOfPawns(pieceColor) >= PAWNS) {
			pawn.setXCoordinate(-1);
			pawn.setYCoordinate(-1);
			return pawn;
		}
		pieces[xCoordinate][yCoordinate] = pawn;
		pawn.setChessBoard(this);
		pawn.setPieceColor(pieceColor);
		pawn.setXCoordinate(xCoordinate);
		pawn.setYCoordinate(yCoordinate);
		return pawn;
	}
	/*
	 *To check the LegalBoard position
	 */
	public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
		if (xCoordinate < 0 || xCoordinate >= MAX_BOARD_WIDTH) {
			return false;
		}
		if (yCoordinate < 0 || yCoordinate >= MAX_BOARD_HEIGHT) {
			return false;
		}
		return true;
	}
	/*
	 *To check the number of pawns on board
	 */
	private int numberOfPawns(PieceColor pieceColor) {
		int nPawns = 0;
		for (Pawn[] columns: pieces) {
			for (Pawn piece: columns) {
				if ((piece instanceof Pawn) && piece.getPieceColor() == pieceColor) {
					nPawns++;
				}
			}
		}
		return nPawns;
	}
	/*
	 *To check if a piece is already placed in a position
	 */
	public boolean isOccupied(int xCoordinate, int yCoordinate) {
		if(pieces[xCoordinate][yCoordinate]!=null)
			return true;
		else 
			return false;
	}
    //New Move
	public void ChangePosition(Pawn piece, int newX, int newY) {
		pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null;
		pieces[newX][newY] = piece;
	}

 }





