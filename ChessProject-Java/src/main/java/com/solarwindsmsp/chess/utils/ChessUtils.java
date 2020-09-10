package com.solarwindsmsp.chess.utils;

/**
 * Utility class for the chessBoard.
 *
 * @author jr185235
 *
 */
public class ChessUtils {

	/** Static constant for checking the color Black. */
	public static final String COLOR_BLACK = "BLACK";

	/** Static constant for checking the color White. */
	public static final String COLOR_WHITE = "WHITE";

	/** Static constant for checking the type Move. */
	public static final String MOVEMENT_TYPE_MOVE = "MOVE";

	/** Message to display when the wrong player move. */
	public static final String MESSAGE_WRONG_PLAYER = "The {0} {1} cannot be moved because it is the turn of {2}";

	/** Message to display when movement is invalid. */
	public static final String MESSAGE_INVALID_MOVE = "The {0} {1} cannot be moved to the position ({2},{3}) from ({4},{5})";

	/** Message to display when type of movement is invalid. */
	public static final String MESSAGE_INVALID_TYPE_OF_MOVE = "Type of movement not supported.";

	/** Private constructor to restrict instantiation. */
	private ChessUtils() {
		throw new AssertionError("This class should not be instantiated.");
	}

	/** To check if the pieces are in the chessBoard. */
	public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
		return (i >= minValueInclusive && i <= maxValueInclusive);
	}

}
