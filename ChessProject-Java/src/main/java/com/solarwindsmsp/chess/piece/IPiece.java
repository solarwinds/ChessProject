package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.IChessBoard;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

/**
 * Interface for managing the pieces.
 *
 * @author jr185235
 *
 */
public interface IPiece {

	/**
	 * Get the color of the piece.
	 *
	 * @return {@link PieceColor}.
	 */
	public PieceColor getPieceColor();

	/**
	 * Get the type of the piece.
	 *
	 * @return {@link PieceType}.
	 */
	public PieceType getPieceType();

	/**
	 * Get the xCoordinate.
	 *
	 * @return xCoordinate.
	 */
	public int getxCoordinate();

	/**
	 * Get the yCoordinate.
	 *
	 * @return yCoordinate.
	 */
	public int getyCoordinate();

	/**
	 * Check if the initial position is valid.
	 *
	 * @param xCoordinate to be used initially.
	 * @param yCoordinate to be used initially.
	 * @return true if it's a valid initial position. Otherwise, false.
	 */
	boolean isInitialPositionValid(final int xCoordinate, final int yCoordinate);

	/**
	 * Check if the movement is valid.
	 *
	 * @param moveToPositionX - Targeted Position X.
	 * @param moveToPositionY - Targeted Position Y.
	 * @return true if it's a valid movement. Otherwise, false.
	 */
	boolean isValidMove(final int moveToPositionX, final int moveToPositionY);

	/**
	 * Move the piece to a new coordinate.
	 *
	 * @param movementType - {@link MovementType}.
	 * @param newX         - Position X to be moved.
	 * @param newY         - Position X to be moved.
	 */
	void move(final MovementType movementType, final int newX, final int newY);

	/**
	 * Assign the chessCoard to the piece.
	 *
	 * @param {@link ChessBoard} to be set.
	 */
	void setChessBoard(final IChessBoard chessBoard);

	/**
	 * Add xCoordinate to the piece.
	 *
	 * @param xCoordinate to be set.
	 */
	public void setxCoordinate(final int xCoordinate);

	/**
	 * Add yCoordinate to the piece.
	 *
	 * @param yCoordinate to be set.
	 */
	public void setyCoordinate(final int yCoordinate);

}
