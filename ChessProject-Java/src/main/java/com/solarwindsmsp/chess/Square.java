package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.pieces.IPiece;

/**
 * Class to represent a square in the chessBoard.
 *
 * @author jr185235
 */
public class Square {

	/** The piece to be in the square. */
	private IPiece piece;

	/**
	 * Constructor.
	 *
	 * @param piece to added into the square.
	 */
	public Square(IPiece piece) {
		this.piece = piece;
	}

	/** @return {@link #piece}. */
	public IPiece getPiece() {
		return piece;
	}

	/** @param piece to be set. */
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}

}
