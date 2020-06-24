package com.solarwindsmsp.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.solarwindsmsp.chess.board.Cell;
import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.exception.DuplicatePieceOnSameCellException;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.Piece;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.strategy.BoardInitializationStrategy;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChessBoardTest {

    @Mock
    private BoardInitializationStrategy initializationStrategy;

    @InjectMocks
    private ChessBoard testSubject;

    @BeforeEach
    public void setUp(){
        Mockito.lenient().when(initializationStrategy.getBoardHeight()).thenReturn(8);
        Mockito.lenient().when(initializationStrategy.getBoardWidth()).thenReturn(8);
    }


    //Merged testIsLegalBoardPosition_True_X_equals_5_Y_equals_5 and
    // testIsLegalBoardPosition_True_X_equals_0_Y_equals_0 tests.
    @ParameterizedTest
    @MethodSource("legalCoordinatesProvider")
    public void givenLegalBoardPositions_whenLegalBoardPositionCalled_ThenTrueReturned(int xCoordinate, int yCoordinate){
        // Arrange & Act
        boolean isValidPosition = testSubject.isLegalBoardPosition(xCoordinate, yCoordinate);

        // Assert
        assertTrue(isValidPosition,
            String.format("For x coordinate = %s and y coordinate = %s "
                + "isLegalBoardPosition should return true", xCoordinate, yCoordinate));
    }

    //Merged testIsLegalBoardPosition_False_X_equals_11_Y_equals_5,
    // testIsLegalBoardPosition_False_X_equals_0_Y_equals_9,
    // testIsLegalBoardPosition_False_X_equals_11_Y_equals_0 and
    // testIsLegalBoardPosition_False_For_Negative_Y_Values tests
    @ParameterizedTest
    @MethodSource("illegalCoordinatesProvider")
    public void givenIllegalBoardPositions_whenLegalBoardPositionCalled_ThenFalseReturned(int xCoordinate, int yCoordinate){
        // Arrange & Act
        boolean isValidPosition = testSubject.isLegalBoardPosition(xCoordinate, yCoordinate);

        // Assert
        assertFalse(isValidPosition,
            String.format("For x coordinate = %s and y coordinate = %s "
                + "isLegalBoardPosition should return false", xCoordinate, yCoordinate));
    }

    @Test
    public void givenTwoPiecesWithSameCoordinates_WhenTryingToAddSecondPiece_DuplicateCellExceptionThrown() {
        // Arrange
        int xCoordinate = 6;
        int yCoordinate = 3;
        Piece firstPawn = new Pawn(PieceColor.BLACK);
        Piece secondPawn = new Pawn(PieceColor.BLACK);
        Cell[][] mockedBoard = getMockBoard();
        mockedBoard[xCoordinate][yCoordinate].setPiece(firstPawn);
        Mockito.when(initializationStrategy.initializeBoard()).thenReturn(mockedBoard);
        testSubject = new ChessBoard(initializationStrategy);

        // Act && Assert
        assertThrows(DuplicatePieceOnSameCellException.class,
            () -> testSubject.addPiece(secondPawn, xCoordinate, yCoordinate));
    }

    private Cell[][] getMockBoard(){
        Cell[][] board = new Cell[8][8];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = new Cell(i, j, null);
            }
        }
        return board;
    }

    static Stream<Arguments> legalCoordinatesProvider() {
        return Stream.of(
            arguments(0, 0),
            arguments(5, 5)
        );
    }

    static Stream<Arguments> illegalCoordinatesProvider() {
        return Stream.of(
            arguments(11, 5),
            arguments(0, 9),
            arguments(11, 0),
            arguments(5, -1)
        );
    }

    // Useless test in case board has an initialization strategy.
    /*@Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / 7;
            testSubject.addPiece(pawn, 6 + row, i % 7, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % 7, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }*/
}