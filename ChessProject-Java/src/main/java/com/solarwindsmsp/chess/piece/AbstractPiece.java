package com.solarwindsmsp.chess.piece;

import java.text.MessageFormat;

import com.solarwindsmsp.chess.IChessBoard;
import com.solarwindsmsp.chess.piece.attribute.Coordinate;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

/**
 * Abstract class to provide common implementation as well as delegating the
 * responsibility of implementing the behaviour to all the inheriting
 * subclasses.
 *
 * NOTE: Getter and Setter for xCoordinate and yCoordinate have been refactored
 * to follow the naming convention.
 *
 * @author jr185235
 *
 */
public abstract class AbstractPiece implements IPiece {

	/** {@link PieceColor}. */
	private final PieceColor pieceColor;

	/** {@link PieceType}. */
	private final PieceType pieceType;

	/** {@link Coordinate}. */
	private final Coordinate coordinate;

	/** {@link IChessBoard}. */
	private IChessBoard chessBoard;

	/**
	 * Constructor.
	 *
	 * @param color of the piece.
	 * @param type  of piece.
	 */
	public AbstractPiece(final PieceColor color, final PieceType type) {
		pieceType = type;
		pieceColor = color;
		coordinate = new Coordinate(-1, -1);
	}

	/** @return {@link #chessBoard}. */
	public IChessBoard getChessBoard() {
		return chessBoard;
	}

	/** @return {@link #pieceColor}. */
	@Override
	public PieceColor getPieceColor() {
		return pieceColor;
	}

	/** @return {@link #pieceType}. */
	@Override
	public PieceType getPieceType() {
		return pieceType;
	}

	/** @return the position X. */
	@Override
	public int getxCoordinate() {
		return coordinate.getxCoordinate();
	}

	/** @return the position Y. */
	@Override
	public int getyCoordinate() {
		return coordinate.getyCoordinate();
	}

	/** @param {@link IChessBoard} to be set. */
	@Override
	public void setChessBoard(final IChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	/** @param xCoordinate to be set. */
	@Override
	public void setxCoordinate(final int xCoordinate) {
		coordinate.setxCoordinate(xCoordinate);
	}

	/** @param yCoordinate to be set. */
	@Override
	public void setyCoordinate(final int yCoordinate) {
		coordinate.setyCoordinate(yCoordinate);
	}

	@Override
	public String toString() {
		return MessageFormat.format("Current X: {1}{0}Current Y: {2}{0}Piece: {3}{0} Color: {4}",
				System.lineSeparator(), getxCoordinate(), getyCoordinate(), getPieceType().name(), getPieceColor());
	}

}
