package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceFactory;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class PieceFactoryTest {

    @Test
    public void pieceFactory_createPawn() {
        ChessPiece piece = PieceFactory.createPiece(PieceType.PAWN, PieceColor.BLACK);
        Assert.assertNotNull(piece);
        Assert.assertEquals(piece.getPieceColor(), PieceColor.BLACK);
        Assert.assertTrue(piece instanceof Pawn);
    }

    @Test
    public void pieceFactory_createBishop() {
        ChessPiece piece = PieceFactory.createPiece(PieceType.BISHOP, PieceColor.BLACK);
        Assert.assertNull(piece);
    }
}
