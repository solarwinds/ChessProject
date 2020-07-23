package com.solarwindsmsp.chess.game;

import com.solarwindsmsp.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.solarwindsmsp.chess.constants.Constants.MAX_BOARD_INDEX;
import static com.solarwindsmsp.chess.constants.Constants.MIN_BOARD_INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @BeforeEach
    public void setUp() {
        testSubject = new ChessBoard();
    }

    @Test
    public void testPieces_shouldCreatePieces() {
        assertNotNull(testSubject.getPieces());
    }

    @Test
    public void testHas_MaxBoardIndex_of_7() {
        assertEquals(7, MAX_BOARD_INDEX);
    }

    @Test
    public void testHas_MinBoardIndex_of_0() {
        assertEquals(0, MIN_BOARD_INDEX);
    }

    @Test
    public void addPiece_shouldAvoidDuplicatePositioning_BlackPawn() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.addPiece(secondPawn, 6, 3, PieceColor.BLACK);

        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());

        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void addPiece_shouldAvoidDuplicatePositioning_WhitePawn() {
        Pawn firstPawn = new Pawn(PieceColor.WHITE);
        Pawn secondPawn = new Pawn(PieceColor.WHITE);

        testSubject.addPiece(firstPawn, 1, 3, PieceColor.WHITE);
        testSubject.addPiece(secondPawn, 1, 3, PieceColor.WHITE);

        assertEquals(1, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());

        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetInvalidCoordinates_whenInvalidPosition() {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(pawn, 8, 3, PieceColor.BLACK);

        assertEquals(-1, pawn.getXCoordinate());
        assertEquals(-1, pawn.getYCoordinate());
    }

    @Test
    public void addPiece_shouldSetInvalidCoordinates_whenColorMismatch() {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        testSubject.addPiece(pawn, 8, 3, PieceColor.WHITE);

        assertEquals(-1, pawn.getXCoordinate());
        assertEquals(-1, pawn.getYCoordinate());
    }

    @Test
    public void addPiece_shouldLimitTheNumberOfPawns() {
        for (int y = 0; y < 10; y++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);

            testSubject.addPiece(pawn, 6, y, PieceColor.BLACK);

            if (y <= MAX_BOARD_INDEX) {
                assertEquals(6, pawn.getXCoordinate());
                assertEquals(y, pawn.getYCoordinate());
            } else {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
}