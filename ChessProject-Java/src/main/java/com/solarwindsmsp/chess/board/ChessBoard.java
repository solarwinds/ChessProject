package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.util.ChessUtils;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class ChessBoard {

    public static final Logger logger = Logger.getLogger(ChessBoard.class.getName());

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    /*
     * Representation of the board, in a 2D Array:
     * {[7,0],[7,1],[7,2],[7,3],[7,4],[7,5],[7,6],[7,7]}
     * {[6,0],[6,1],[6,2],[6,3],[6,4],[6,5],[6,6],[6,7]}
     * {[5,0],[5,1],[5,2],[5,3],[5,4],[5,5],[5,6],[5,7]}
     * {[4,0],[4,1],[4,2],[4,3],[4,4],[4,5],[4,6],[4,7]}
     * {[3,0],[3,1],[3,2],[3,3],[3,4],[3,5],[3,6],[3,7]}
     * {[2,0],[2,1],[2,2],[2,3],[2,4],[2,5],[2,6],[2,7]}
     * {[1,0],[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]}
     * {[0,0],[0,1],[0,2],[0,3],[0,4],[0,5],[0,6],[0,7]}
     *
     * Algebraic Notation:
     * A8, B8, C8, D8, E8, F8, G8, H8
     * A7, B7, C7, D7, E7, F7, G7, H7
     * A6, B6, C6, D6, E6, F6, G6, H6
     * A5, B5, C5, D5, E5, F5, G5, H5
     * A4, B4, C4, D4, E4, F4, G4, H4
     * A3, B3, C3, D3, E3, F3, G3, H3
     * A2, B2, C2, D2, E2, F2, G2, H2
     * A1, B1, C1, D1, E1, F1, G1, H1
     */
    private final ChessPiece[][] squares;

    /*
     * TODO: Sensible extension could be to render SQUARES that contain PIECES, as opposed to having pieces directly
     *  on the board.
     */
    public ChessBoard() {
        squares = new ChessPiece[MAX_BOARD_WIDTH + 1][MAX_BOARD_HEIGHT + 1];
    }

    public void addPiece(ChessPiece piece, int rowCoordinate, int colCoordinate)
    {
        logger.info(MessageFormat.format("Attempting to add a {0} to {1} [{2},{3}] coordinates", piece.getPieceType(),
                ChessUtils.coordinateToAlgebraic(rowCoordinate, colCoordinate), rowCoordinate, colCoordinate));

        if (!isLegalBoardPosition(rowCoordinate, colCoordinate)) {
            logger.warning(MessageFormat.format("Cannot add piece to invalid location [{0},{1}]", rowCoordinate, colCoordinate));
            piece.removeFromBoard();
            return;
        }

        if (isSpaceOccupied(rowCoordinate, colCoordinate)) {
            logger.warning(MessageFormat.format("Cannot populate a square that already has a piece at location [{0},{1}]", rowCoordinate, colCoordinate));
            piece.removeFromBoard();
            return;
        }

        if (!piece.isValidSpaceToAdd(rowCoordinate, colCoordinate)) {
            logger.warning(MessageFormat.format("Cannot add piece to location as invalid start position, [{0},{1}]", rowCoordinate, colCoordinate));
            piece.removeFromBoard();
            return;
        }

        piece.setRowCoordinate(rowCoordinate);
        piece.setColCoordinate(colCoordinate);
        squares[rowCoordinate][colCoordinate] = piece;
    }

    /**
     * Determine if the requested coordinates are within the game board
     * @param rowCoordinate the row (or X) coordinate
     * @param colCoordinate the column (or Y) coordinate
     * @return true if legal position, false otherwise.
     */
    public boolean isLegalBoardPosition(int rowCoordinate, int colCoordinate) {
        return ChessUtils.isValidCoordinate(rowCoordinate, colCoordinate);
    }

    /**
     * Determine if the space is occupied or not
     * @param rowCordinate the row (or X) coordinate of the space to interrogate
     * @param colCoordinate the column (or Y) coordinate of the space to interrogate
     * @return true if occupied, false otherwise.
     */
    public boolean isSpaceOccupied(int rowCordinate, int colCoordinate) {
        return (squares[rowCordinate][colCoordinate] != null);
    }
}
