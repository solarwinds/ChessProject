package com.solarwindsmsp.chess;


import com.solarwindsmsp.chess.models.Piece;
import com.solarwindsmsp.chess.models.PieceFactory;
import org.junit.Test;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;

public class PieceFactoryTest {

    @Test
    public void testPieceFactory_Supports_All_Types() {
        Stream.of(Piece.Type.values()).forEach(type -> {
            Piece piece = PieceFactory.createPiece(type, PieceColor.BLACK);
            assertNotNull(piece);
        });
    }
}
