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

    private ChessPiece[][] squares;

    public ChessBoard() {
        squares = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(ChessPiece piece, int rowCoordinate, int colCoordinate, PieceColor pieceColor)
    {
        logger.info(MessageFormat.format("Attempting to add a {0} to [{1},{2}] coordinates", piece.getPieceType(), rowCoordinate, colCoordinate));

        if (!isLegalBoardPosition(rowCoordinate, colCoordinate)) {
            logger.warning(MessageFormat.format("Cannot add piece to invalid location [{0},{1}]", rowCoordinate, colCoordinate));
            return;
        }

        if (isSpaceOccupied(rowCoordinate, colCoordinate)) {
            //throw new SquareAlreadyPopulatedException(rowCoordinate, colCoordinate);
            logger.warning(MessageFormat.format("Cannot populate a square that already has a piece at location [{0},{1}]", rowCoordinate, colCoordinate));
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
