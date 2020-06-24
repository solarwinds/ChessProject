package com.solarwindsmsp.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.solarwindsmsp.chess.board.Cell;
import com.solarwindsmsp.chess.exception.UnsupportedMoveException;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.Piece;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PawnTest {

    private static final Player PLAYER_ONE = new Player("PLAYER1", PieceColor.WHITE);
    private static final Player PLAYER_TWO = new Player("PLAYER2", PieceColor.BLACK);

    private ChessGame testSubject;

    @BeforeEach
    public void setUp() {
        this.testSubject = new ChessGame(PLAYER_ONE, PLAYER_TWO);
    }

    @Test
    public void testChessBoard_Add_Sets_Coordinates() {
        // Arrange
        Cell[][] cells = getMockedCells();
        Piece pawn = new Pawn(PieceColor.BLACK);
        cells[6][3].setPiece(pawn);
        testSubject.getChessBoard().setBoardCells(cells);

        // Act & Assert
        assertEquals(pawn, testSubject.getChessBoard().getCell(6, 3).getPiece());
    }

    /* Tested in previous test
    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.addPiece(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    } */


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        // Arrange
        Cell[][] cells = getMockedCells();
        Piece pawn = new Pawn(PieceColor.WHITE);
        cells[6][3].setPiece(pawn);
        testSubject.getChessBoard().setBoardCells(cells);

        // Act && Assert
        assertThrows(UnsupportedMoveException.class, () ->
            testSubject.movePiece(PLAYER_ONE, 6, 3, 7, 3));
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        // Arrange
        Cell[][] cells = getMockedCells();
        Piece pawn = new Pawn(PieceColor.WHITE);
        cells[6][3].setPiece(pawn);
        testSubject.getChessBoard().setBoardCells(cells);

        // Act && Assert
        assertThrows(UnsupportedMoveException.class, () ->
            testSubject.movePiece(PLAYER_ONE, 6, 3, 5, 3));
    }

    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        // Arrange
        Cell[][] cells = getMockedCells();
        Piece pawn = new Pawn(PieceColor.WHITE);
        cells[6][3].setPiece(pawn);
        testSubject.getChessBoard().setBoardCells(cells);

        // Act
        testSubject.movePiece(PLAYER_ONE, 6, 3, 6, 4);

        // Assert
        assertNull(testSubject.getChessBoard().getCell(6, 3).getPiece());
        assertEquals(pawn, testSubject.getChessBoard().getCell(6, 4).getPiece());
    }

    private Cell[][] getMockedCells() {
        Cell[][] boardCells = new Cell[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                boardCells[i][j] = new Cell(i, j, null);
            }
        }

        return boardCells;
    }

}