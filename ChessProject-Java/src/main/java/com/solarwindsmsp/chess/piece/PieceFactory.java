package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

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
