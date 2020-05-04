package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.enums.MovementType;
import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.models.Cell;
import com.solarwindsmsp.chess.models.ChessBoard;
import com.solarwindsmsp.chess.models.ChessPiece;
import com.solarwindsmsp.chess.validators.PawnMovementValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.solarwindsmsp.chess.enums.PieceColor.BLACK;
import static com.solarwindsmsp.chess.enums.PieceColor.WHITE;
import static com.solarwindsmsp.chess.enums.PieceType.PAWN;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PawnMovementValidatorTest {

    private final PieceColor pieceColor;
    private final Cell oldCell;
    private final Cell newCell;

    public PawnMovementValidatorTest(final PieceColor pieceColor, final Cell oldCell, final Cell newCell) {
        this.pieceColor = pieceColor;
        this.oldCell = oldCell;
        this.newCell = newCell;
    }

    @Test
    public void testPawnMovementValidator_LegalCoordinates_Forward() {
        PawnMovementValidator testSubject = new PawnMovementValidator();
        ChessPiece pawn = new ChessPiece(PAWN, this.pieceColor);
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.addPiece(pawn, this.oldCell.getXCoordinate(), this.oldCell.getYCoordinate());
        boolean isMovementValid = testSubject.isMovementValid(MovementType.MOVE, pawn, this.newCell);

        assertEquals(TRUE, isMovementValid);
    }

    @Parameterized.Parameters(name = "PawnMovementValidatorTest[{index}]({0},{1},{2})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { BLACK, new Cell(6,3), new Cell(6,2) },
                { BLACK, new Cell(6,6), new Cell(6,5) },
                { BLACK, new Cell(1,7), new Cell(1,5) },
                { WHITE, new Cell(1,1), new Cell(1,2) },
                { WHITE, new Cell(1,1), new Cell(1,3) },
                { WHITE, new Cell(1,4), new Cell(1,5) },
        });
    }

}
