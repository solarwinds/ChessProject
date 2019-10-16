package com.solarwindsmsp.chess.piece.factory;

import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.piece.PieceName;
import com.solarwindsmsp.chess.piece.factory.ChessPieceFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

public class ChessPieceFactoryTest {

    @Test
    public void testChessPieceFactory_Create__Black_Pawn() {
        ChessPiece chessPiece = ChessPieceFactory.create(PieceName.PAWN, PieceColor.BLACK);

        assertSame(chessPiece.getName(), PieceName.PAWN);
        assertSame(chessPiece.getPieceColor(), PieceColor.BLACK);
    }

    @Test
    public void testChessPieceFactory_Create__White_Pawn() {
        ChessPiece chessPiece = ChessPieceFactory.create(PieceName.PAWN, PieceColor.WHITE);

        assertSame(chessPiece.getName(), PieceName.PAWN);
        assertSame(chessPiece.getPieceColor(), PieceColor.WHITE);
    }
}