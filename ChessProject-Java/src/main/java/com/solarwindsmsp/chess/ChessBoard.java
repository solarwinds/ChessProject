package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exceptions.BoardPositionNotEmptyException;
import com.solarwindsmsp.chess.exceptions.MaximumNumberOfPiecesReached;
import com.solarwindsmsp.chess.pieces.Piece;
import com.solarwindsmsp.chess.pieces.PieceColor;

import java.util.Arrays;

/**
 * Chess board class
 */
public class ChessBoard {

    public final static int BOARD_WIDTH = 8;
    public final static int BOARD_HEIGHT = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[BOARD_WIDTH][BOARD_HEIGHT];
    }

    /**
     * Add piece to chess board
     * @param piece Piece
     * @param xCoordinate Row of position
     * @param yCoordinate Column of position
     */
    public void addPiece(Piece piece, int xCoordinate, int yCoordinate) throws BoardPositionNotEmptyException, MaximumNumberOfPiecesReached {
        if (!isEmptyBoardPosition(xCoordinate, yCoordinate)) {
            throw new BoardPositionNotEmptyException(piece, xCoordinate, yCoordinate, pieces[xCoordinate][yCoordinate]);
        }

        if (getCurrentNumberOfPieces(piece.getClass(), piece.getPieceColor()) >= piece.getMaxNumberOfPieces()) {
            throw new MaximumNumberOfPiecesReached(piece, xCoordinate, yCoordinate, piece.getMaxNumberOfPieces());
        }

        pieces[xCoordinate][yCoordinate] = piece;
        piece.assignPieceToChessBoard(this, xCoordinate, yCoordinate);
    }

    /**
     * Get current number of pieces
     * @param clazz Class of piece type
     * @param color Color of the piece
     * @return current number of pieces
     */
    public long getCurrentNumberOfPieces(Class<? extends Piece> clazz, PieceColor color) {
        return Arrays.stream(pieces)
            .flatMap(x -> Arrays.stream(x))
            .filter(piece -> piece != null)
            .filter(piece -> piece.getClass().equals(clazz))
            .filter(piece -> piece.getPieceColor() == color)
            .count();
    }

    /**
     * Decides if position of the chess board is empty
     * @param xCoordinate Row of position
     * @param yCoordinate Column of position
     * @return if position is empty
     */
    public boolean isEmptyBoardPosition(int xCoordinate, int yCoordinate) {
        return isLegalBoardPosition(xCoordinate, yCoordinate) &&
               pieces[xCoordinate][yCoordinate] == null;
    }

    /**
     * Decides if position is on the chess board
     * @param xCoordinate Row of position
     * @param yCoordinate Column of position
     * @return if position is on the chess board
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < BOARD_WIDTH &&
               yCoordinate >= 0 && yCoordinate < BOARD_HEIGHT;
    }

    /**
     * Commits a piece movement to the chess board
     * @param piece Piece
     * @param newX Row of new position
     * @param newY Column of new position
     */
    public void commitMove(Piece piece, int newX, int newY) {
        pieces[piece.getXCoordinate()][piece.getYCoordinate()] = null;
        pieces[newX][newY] = piece;
        piece.assignPieceToChessBoard(this, newX, newY);
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}
