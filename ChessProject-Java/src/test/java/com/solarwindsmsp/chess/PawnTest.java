package com.solarwindsmsp.chess;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

/**
 * Tests checking the behaviour of the Pawn.
 */
public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;
    private Map<Pair<Integer, Integer>, Piece> pieces;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.pieces = chessBoard.getMapOfPieces();
        pieces.clear();
        Pawn.numberOfWhitePawns = 0;
        Pawn.numberOfBlackPawns = 0;
    }

    /**
     * We now check whether the correct coordinates are contained in the map, as the add() method no longer
     * gets passed coordinates. This applies to all add_pawn tests.
     *
     * @throws Exception
     */
    @Test
    public void testAdd_Pawn_Sets_XCoordinate() throws Exception {
        this.testSubject = new Pawn(PieceColor.BLACK, 6, 3, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertEquals(6, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
    }

    @Test
    public void testAdd_Pawn_Sets_YCoordinate() throws Exception {
        this.testSubject = new Pawn(PieceColor.BLACK, 6, 3, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertEquals(3, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 6, 3, chessBoard);
        chessBoard.add(testSubject);
        testSubject.move(MovementType.MOVE, 7, 3);
        Assertions.assertEquals(6, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(3, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 6, 3, chessBoard);
        chessBoard.add(testSubject);
        testSubject.move(MovementType.MOVE, 4, 3);
        Assertions.assertEquals(6, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(3, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    /**
     * Seen from the "black" perspective, hence the decreasing y coordinate value.
     */
    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() throws Exception {
        this.testSubject = new Pawn(PieceColor.BLACK, 6, 3, chessBoard);
        chessBoard.add(testSubject);
        testSubject.move(MovementType.MOVE, 6, 2);
        Assertions.assertEquals(6, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(2, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    /**
     * Check if a pawn can be moved forward by two squares.
     *
     * @throws Exception
     */
    @Test
    public void testPawn_Move_LegalCoordinates_TwoForward_UpdatesCoordinates() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 2, chessBoard);
        chessBoard.add(testSubject);
        testSubject.move(MovementType.MOVE, 1, 4);
        Assertions.assertEquals(1, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(4, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    @Test
    /**
     * Check that pawn is not moved forward by two squares if a piece is in the way.
     */
    public void testPawn_Move_IllegalCoordinates_TwoForward_Blocked_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 2, chessBoard);
        Pawn testSubjectTwo = new Pawn(PieceColor.BLACK, 1, 3, chessBoard);
        chessBoard.add(testSubject);
        chessBoard.add(testSubjectTwo);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 1, 4));
    }

    /**
     * Check that the pawn is not moved, if the specified new coordinates are off the board.
     *
     * @throws Exception
     */
    @Test
    public void testPawn_Move_IllegalCoordinates_XCoordinate_OutOfBounds_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 8, 2, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 9, 4));
        Assertions.assertEquals(8, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(2, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    /**
     * Check that a white pawn cannot be moved backwards.
     *
     * @throws Exception
     */
    @Test
    public void testWhitePawn_Move_IllegalCoordinates_Backward_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 2, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 1, 1));
        Assertions.assertEquals(1, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(2, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    /**
     * Check that a black pawn cannot be moved backwards.
     *
     * @throws Exception
     */
    @Test
    public void testBlackPawn_Move_IllegalCoordinates_Backward_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 7, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 1, 6));
        Assertions.assertEquals(1, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getXCoordinate());
        Assertions.assertEquals(7, pieces.get(new Pair<>(testSubject.xCoordinate, testSubject.yCoordinate)).getYCoordinate());
    }

    @Test
    /**
     * Check that the not yet implemented capture() method throws the appropriate exception.
     */
    public void testPawn_Capture_Throws_UnsupportedOperationException() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 5, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> testSubject.move(MovementType.CAPTURE, 2,
                6));
    }

    /**
     * Check that a pawn cannot be teleported across the board and must follow standard move rules.
     *
     * @throws Exception
     */
    @Test
    public void testPawn_Move_IllegalCoordinates_Teleport_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 2, chessBoard);
        chessBoard.add(testSubject);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 6, 6));
    }

    /**
     * Check that a pawn does not move to a square that is already occupied by another piece.
     *
     * @throws Exception
     */
    @Test
    public void testPawn_Move_IllegalCoordinates_SquareOccupied_DoesNotMove() throws Exception {
        this.testSubject = new Pawn(PieceColor.WHITE, 1, 2, chessBoard);
        Pawn testSubjectTwo = new Pawn(PieceColor.BLACK, 1, 3, chessBoard);
        chessBoard.add(testSubject);
        chessBoard.add(testSubjectTwo);
        Assertions.assertFalse(testSubject.move(MovementType.MOVE, 1, 3));
    }

}