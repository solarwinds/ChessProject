package com.solarwindsmsp.chess;

import java.util.Deque;

import com.solarwindsmsp.chess.pieces.IPiece;
import com.solarwindsmsp.chess.pieces.attributes.PieceColor;

/**
 * Interface for managing the chessBoard.
 *
 * @author jr185235
 *
 */
public interface IChessBoard {

	/** Get the history of the movement. */
	Deque<IPiece> getMovementHistory();

	/** Get the turn, who needs to move. */
	PieceColor getTurnToMove();

	/**
	 * Change the turn.
	 *
	 * @param turnToMove.
	 */
	public void setTurnToMove(final PieceColor turnToMove);

	/**
	 * Added the piece to the chessBoard.
	 *
	 * @param piece       to be added.
	 * @param xCoordinate - The row, where is going to be added.
	 * @param yCoordinate - The column, where is going to be added.
	 */
	void addPiece(final IPiece piece, final int xCoordinate, final int yCoordinate);

	/**
	 * Check if the position is inside of chessBoard.
	 *
	 * @param xCoordinate - The row of the chessBoard.
	 * @param yCoordinate - The column of the chessBoard.
	 * @return true if it's inside.Otherwise, false.
	 */
	boolean isLegalBoardPosition(final int xCoordinate, final int yCoordinate);

}
