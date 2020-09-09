package com.solarwindsmsp.chess.utils;

import com.solarwindsmsp.chess.IChessBoard;
import com.solarwindsmsp.chess.pieces.PieceFactory;

/**
 * Utility class for testing with common objects to avoid duplicate code.
 *
 */
public class TestUtils {

	/** CheckBoard for testing. */
	protected IChessBoard testChessBoard;

	/** Factory of piece to get a piece. */
	protected final PieceFactory pieceFactory = new PieceFactory();

}
