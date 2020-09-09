package com.solarwindsmsp.chess.pieces;

import java.text.MessageFormat;
import java.util.logging.Logger;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.pieces.attributes.MovementType;
import com.solarwindsmsp.chess.pieces.attributes.PieceColor;
import com.solarwindsmsp.chess.pieces.attributes.PieceType;
import com.solarwindsmsp.chess.utils.ChessUtils;

/**
 * Class to represent a Pawn.
 *
 */
public class Pawn extends AbstractPiece {

	/**
	 * A Logger object to log messages.
	 *
	 * @See Logger.
	 */
	private static final Logger logger = Logger.getLogger(Pawn.class.getName());

	/** Initial X coordinate for the Pawns. */
	public static final int INITIAL_BLACK_X_COORDINATE = 6;

	/** Initial Y coordinate for the Pawns. */
	public static final int INITIAL_WHITE_X_COORDINATE = 1;

	/**
	 * Constructor.
	 *
	 * @param pieceColor - Color of piece.
	 */
	public Pawn(final PieceColor pieceColor) {
		super(pieceColor, PieceType.PAWN);
	}

	@Override
	public boolean isInitialPositionValid(final int xCoordinate, final int yCoordinate) {
		if (COLOR_BLACK.equals(getPieceColor().name())) {
			return (xCoordinate == INITIAL_BLACK_X_COORDINATE)
					&& (yCoordinate >= 0 && yCoordinate <= ChessBoard.MAX_BOARD_WIDTH);
		} else {
			return (xCoordinate == INITIAL_WHITE_X_COORDINATE)
					&& (yCoordinate >= 0 && yCoordinate <= ChessBoard.MAX_BOARD_WIDTH);
		}

	}

	@Override
	public boolean isValidMove(final int newX, final int newY) {

		/*
		 * Check if - It didn't move. - It's moving sideways. - It's moving out of the
		 * chessBoard.
		 *
		 * NOTE: CAPTURE is not implemented yet.
		 */
		if ((getxCoordinate() == newX && getyCoordinate() != newY)
				|| !ChessUtils.between(newX, 0, ChessBoard.MAX_BOARD_HEIGHT)
				|| !ChessUtils.between(newY, 0, ChessBoard.MAX_BOARD_WIDTH)) {
			return false;
		}

		if (COLOR_BLACK.equals(getPieceColor().name())) {
			// Only allow to move backward (BLACK).
			if (getxCoordinate() > newX) {
				// Check if it's the first movement
				if (getxCoordinate() == INITIAL_BLACK_X_COORDINATE) {
					return (getxCoordinate() - newX) <= 2;
				} else {
					return (getxCoordinate() - newX) == 1;
				}
			}
			return false;

		} else {
			// Only allow to move forward (WHITE).
			if (getxCoordinate() < newX) {
				// Check if it's the first movement
				if (getxCoordinate() == INITIAL_WHITE_X_COORDINATE) {
					return (newX - getxCoordinate()) <= 2;
				} else {
					return (newX - getxCoordinate()) == 1;
				}
			}
			return false;

		}

	}

	@Override
	public void move(final MovementType movementType, final int newX, final int newY) {
		// Check if it's right color is moving the piece.
		final String turnToMove = getChessBoard().getTurnToMove().name();
		if (!getPieceColor().name().equals(turnToMove)) {
			final String message = MessageFormat.format(MESSAGE_WRONG_PLAYER, getPieceColor().name(),
					getPieceType().name(), turnToMove);
			logger.info(message);
			return;

		}
		if (MOVEMENT_TYPE_MOVE.equals(movementType.name())) {
			if (isValidMove(newX, newY)) {
				setxCoordinate(newX);
				setyCoordinate(newY);
				if (PieceColor.BLACK.name().equals(turnToMove)) {
					getChessBoard().setTurnToMove(PieceColor.WHITE);
				} else {
					getChessBoard().setTurnToMove(PieceColor.BLACK);
				}
				getChessBoard().getMovementHistory().push(this);
			} else {
				final String message = MessageFormat.format(MESSAGE_INVALID_MOVE, getPieceColor().name(),
						getPieceType().name(), newX, newY, getxCoordinate(), getyCoordinate());
				logger.info(message);
			}

		} else {
			throw new UnsupportedOperationException(MESSAGE_INVALID_TYPE_OF_MOVE);
		}

	}

}
