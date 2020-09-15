package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class PieceFactory {

    public static final Logger logger = Logger.getLogger(PieceFactory.class.getName());

    public static ChessPiece createPiece(PieceType type, PieceColor color) {
        logger.info(MessageFormat.format("Requested a piece ({0}) in color ({1})", type, color));

        if (PieceType.PAWN == type) {
            return new Pawn(color);
        }

        logger.warning(MessageFormat.format("Factory cannot construct of type {0}", type));
        return null;
    }
}
