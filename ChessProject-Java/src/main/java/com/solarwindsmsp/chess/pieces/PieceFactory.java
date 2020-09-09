package com.solarwindsmsp.chess.pieces;

import com.solarwindsmsp.chess.pieces.attributes.PieceColor;
import com.solarwindsmsp.chess.pieces.attributes.PieceType;

public class PieceFactory {

	public AbstractPiece getPiece(PieceType pieceType, PieceColor pieceColor) {
		if (pieceType == null) {
			return null;
		}
		if (PieceType.PAWN.name().equals(pieceType.name())) {
			return new Pawn(pieceColor);
		}

		return null;
	}

}
