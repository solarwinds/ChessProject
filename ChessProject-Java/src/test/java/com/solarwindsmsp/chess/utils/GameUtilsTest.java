package com.solarwindsmsp.chess.utils;

import com.solarwindsmsp.chess.game.ChessBoard;
import com.solarwindsmsp.chess.game.PieceColor;
import com.solarwindsmsp.chess.pieces.ChessPiece;
import com.solarwindsmsp.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.solarwindsmsp.chess.utils.GameUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameUtilsTest {
    private ChessBoard chessBoard;
    private ChessPiece chessPiece;

    @BeforeEach
    public void setUp() {
        chessBoard = new ChessBoard();
        chessPiece = new Pawn(PieceColor.WHITE);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void isLegalBoardPosition_validXCoordinate_shouldReturnTrue(int xCoordinate) {
        boolean isValidPosition = isLegalBoardPosition(xCoordinate, 0);

        assertTrue(isValidPosition);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -3, -22, 8, 10, 32})
    public void isLegalBoardPosition_invalidXCoordinate_shouldReturnFalse(int xCoordinate) {
        boolean isValidPosition = isLegalBoardPosition(xCoordinate, 0);

        assertFalse(isValidPosition);
    }

    @Test
    public void positionIsEmpty_shouldReturnTrue() {
        boolean isEmptySpace = positionIsEmpty(chessBoard, 2, 3);

        assertTrue(isEmptySpace);
    }

    @Test
    public void positionIsEmpty_shouldReturnFalse_whenPositionIsOccupied() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        chessBoard.addPiece(pawn, 6, 3, PieceColor.BLACK);

        boolean isEmptySpace = positionIsEmpty(chessBoard, 6, 3);

        assertFalse(isEmptySpace);
    }

    @Test
    public void setValidCoordinatesAndChessBoard_shouldSetCoordinates() {
        setValidCoordinatesAndChessBoard(chessPiece, 1, 3, chessBoard);

        assertEquals(1, chessPiece.getXCoordinate());
        assertEquals(3, chessPiece.getYCoordinate());
        assertEquals(chessBoard, chessPiece.getChessBoard());
    }

    @Test
    public void setInvalidCoordinates_shouldSetInvalidCoordinates() {
        setInvalidCoordinates(chessPiece);

        assertEquals(-1, chessPiece.getXCoordinate());
        assertEquals(-1, chessPiece.getYCoordinate());
        assertNull(chessPiece.getChessBoard());
    }
}
