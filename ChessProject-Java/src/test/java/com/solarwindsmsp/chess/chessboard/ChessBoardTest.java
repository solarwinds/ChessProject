package com.solarwindsmsp.chess.chessboard;

import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.*;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MIN_BOARD_HEIGHT;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertSame;

public class ChessBoardTest {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_7() {
        assertEquals(7, MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(0, 0);

        assertFalse(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_X_equals_5_Y_equals_5() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(5, 5);

        assertFalse(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_11_Y_equals_5() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(11, 5);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_True_X_equals_0_Y_equals_9() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(0, 9);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(11, 0);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_For_Negative_X_Values() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(-1, 5);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testIsIllegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isInvalidPosition = testSubject.isIllegalBoardPosition(5, -1);

        assertTrue(isInvalidPosition);
    }

    @Test
    public void testAdd_False_For_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);

        boolean firstPawnAddResult = testSubject.add(firstPawn, 6, 3);

        Assert.assertTrue(firstPawnAddResult);
        Assert.assertEquals(6, firstPawn.getXCoordinate());
        Assert.assertEquals(3, firstPawn.getYCoordinate());

        Pawn secondPawn = new Pawn(PieceColor.BLACK);

        boolean secondPawnAddResult = testSubject.add(secondPawn, 6, 3);

        Assert.assertFalse(secondPawnAddResult);
    }

    @Test
    public void testAdd_False_For_Exceeded_Limit_Of_Pawns() {
        int exceededBoardWidth = MAX_BOARD_WIDTH + 1;

        for (int i = 0; i <= exceededBoardWidth; i++) {
            Pawn pawn = new Pawn(PieceColor.BLACK);

            if (i == exceededBoardWidth) {
                Assert.assertFalse(testSubject.add(pawn, i, MIN_BOARD_HEIGHT));
            } else {
                Assert.assertTrue(testSubject.add(pawn, i, MIN_BOARD_HEIGHT));
            }
        }
    }

    @Test
    public void testAdd_True_For_Empty_Square() {
        Pawn pawn = new Pawn(PieceColor.BLACK);

        boolean pawnAddResult = testSubject.add(pawn, 6, 0);

        Assert.assertTrue(pawnAddResult);
        Assert.assertTrue(testSubject.getBoard()[6][0].getChessPiece().isPresent());
        assertSame(testSubject.getBoard()[6][0].getChessPiece().get(), pawn);
        Assert.assertEquals(6, pawn.getXCoordinate());
        Assert.assertEquals(0, pawn.getYCoordinate());
    }

    @Test
    public void testMove_False_For_Duplicate_Positioning() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        boolean pawnAddResult = testSubject.add(pawn, 6, 3);
        Assert.assertTrue(pawnAddResult);

        boolean movePieceResult = testSubject.move(5, 3, 6, 3);

        Assert.assertFalse(movePieceResult);
    }

    @Test
    public void testMove_False_For_Illegal_Board_Position() {
        boolean movePieceResult = testSubject.move(5, 3, MAX_BOARD_WIDTH + 1, 3);

        Assert.assertFalse(movePieceResult);
    }

    @Test
    public void testMove_False_For_Missing_Chess_Piece() {
        testSubject.getBoard()[5][3].setPiece(null);

        boolean movePieceResult = testSubject.move(5, 3, 6, 3);

        Assert.assertFalse(movePieceResult);
    }

    @Test
    public void testMove_True_For_Valid_Move() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        boolean pawnAddResult = testSubject.add(pawn, 6, 3);
        Assert.assertTrue(pawnAddResult);

        boolean movePieceResult = testSubject.move(6, 3, 7, 3);

        Assert.assertTrue(movePieceResult);
        Assert.assertFalse(testSubject.getBoard()[6][3].getChessPiece().isPresent());
        Assert.assertTrue(testSubject.getBoard()[7][3].getChessPiece().isPresent());
        assertSame(testSubject.getBoard()[7][3].getChessPiece().get(), pawn);
        Assert.assertEquals(7, pawn.getXCoordinate());
        Assert.assertEquals(3, pawn.getYCoordinate());
    }
}