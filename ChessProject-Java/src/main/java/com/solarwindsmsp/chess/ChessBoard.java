package com.solarwindsmsp.chess;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

import com.solarwindsmsp.chess.pieces.IPiece;
import com.solarwindsmsp.chess.pieces.attributes.PieceColor;
import com.solarwindsmsp.chess.utils.ChessUtils;

/**
 * Implementation for {@link IChessBoard}.
 */
public class ChessBoard implements IChessBoard {

	/**
	 * A Logger object to log messages.
	 *
	 * NOTE: The logger reference is not a constant, but a final reference, and
	 * should NOT be in uppercase. A constant value should be in uppercase.
	 *
	 * @See Logger.
	 */
	private static final Logger logger = Logger.getLogger(ChessBoard.class.getName());

	/** The max number of columns (length) of the chessBoard. */
	public static final int MAX_BOARD_WIDTH = 7;

	/** The max number of rows (height) of the chessBoard. */
	public static final int MAX_BOARD_HEIGHT = 7;

	/** Representation of the chessBoard by a matrix. */
	private final Square[][] board;

	/** The History of the movement */
	private final Deque<IPiece> movementHistory = new ArrayDeque<IPiece>();

	/** The turn, who needs to move. */
	private PieceColor turnToMove = PieceColor.WHITE;

	/**
	 * Default constructor to initialized the chessBoard.
	 */
	public ChessBoard() {
		board = new Square[MAX_BOARD_WIDTH + 1][MAX_BOARD_HEIGHT + 1];
	}

	@Override
	public Deque<IPiece> getMovementHistory() {
		return movementHistory;
	}

	@Override
	public PieceColor getTurnToMove() {
		return turnToMove;
	}

	@Override
	public void setTurnToMove(PieceColor turnToMove) {
		this.turnToMove = turnToMove;
	}

	/**
	 * Check if the chessboard's position is unoccupied.
	 *
	 * @param xCoordinate - The row of the chessBoard.
	 * @param yCoordinate - The column of the chessBoard.
	 * @return true if it's empty.Otherwise, false.
	 */
	private boolean isUnoccupied(final int xCoordinate, final int yCoordinate) {
		return board[xCoordinate][yCoordinate] == null;
	}

	@Override
	public void addPiece(final IPiece piece, final int xCoordinate, final int yCoordinate) {
		// Check if the piece can be added to the chessBoard
		if (isLegalBoardPosition(xCoordinate, yCoordinate) && piece.isInitialPositionValid(xCoordinate, yCoordinate)
				&& isUnoccupied(xCoordinate, yCoordinate)) {
			piece.setxCoordinate(xCoordinate);
			piece.setyCoordinate(yCoordinate);
			// To keep the association between the piece and the chessBoard to be used for
			// future features.
			piece.setChessBoard(this);
			board[xCoordinate][yCoordinate] = new Square(piece);
		} else {
			final String message = MessageFormat.format("The {0} {1} cannot be added to ({2},{3})",
					piece.getPieceColor().name(), piece.getPieceType().name(), xCoordinate, yCoordinate);
			logger.info(message);

		}

	}

	@Override
	public boolean isLegalBoardPosition(final int xCoordinate, final int yCoordinate) {
		return ChessUtils.between(xCoordinate, 0, MAX_BOARD_HEIGHT)
				&& ChessUtils.between(yCoordinate, 0, MAX_BOARD_WIDTH);
	}

}
