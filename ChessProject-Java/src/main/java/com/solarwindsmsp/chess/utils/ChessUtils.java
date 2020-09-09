package com.solarwindsmsp.chess.utils;

public class ChessUtils {

	/** Private constructor to restrict instantiation. */
	private ChessUtils() {
		throw new AssertionError("This class should not be instantiated.");
	}

	/** To check if the pieces are in the chessBoard. */
	public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
		return (i >= minValueInclusive && i <= maxValueInclusive);
	}

}
