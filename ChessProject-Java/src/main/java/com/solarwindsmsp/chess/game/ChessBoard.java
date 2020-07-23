package com.solarwindsmsp.chess.game;

import com.solarwindsmsp.chess.pieces.ChessPiece;

import static com.solarwindsmsp.chess.constants.Constants.MAX_BOARD_INDEX;
import static com.solarwindsmsp.chess.utils.GameUtils.*;

/**
 * Class to define a chess board of 8x8 grid.
 */
public class ChessBoard {

    private final ChessPiece[][] pieces;

    /**
     * Constructor used to create the chess board of 8x8 grid
     */
    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_INDEX + 1][MAX_BOARD_INDEX + 1];
    }

    public ChessPiece[][] getPieces() {
        return pieces;
    }

    /**
     * Chess Piece is added on the board only if:
     * - legal coordinates are given
     * - the position from the given coordinates is empty
     * - the color of the chess piece matches the color given
     * - chess piece starts from its legal position
     *
     * @param chessPiece  chessPiece that would be added on the board
     * @param xCoordinate x coordinate on the board
     * @param yCoordinate y coordinate on the board
     * @param pieceColor  color of the chess piece
     */
    public void addPiece(ChessPiece chessPiece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        setInvalidCoordinates(chessPiece);

        if (hasValidCoordinates(xCoordinate, yCoordinate) &&
                isPositionEmpty(this, xCoordinate, yCoordinate) &&
                isColorMatch(chessPiece, pieceColor) &&
                startsInLegalPosition(chessPiece, xCoordinate)) {

            setValidCoordinatesAndChessBoard(chessPiece, xCoordinate, yCoordinate, this);

            pieces[xCoordinate][yCoordinate] = chessPiece;
        }
    }

    /**
     * Updates the new position of a given chess piece
     *
     * @param chessPiece     chessPiece for whom the position would be updated
     * @param oldXCoordinate old x coordinate of the piece, that has to be cleared
     * @param oldYCoordinate old y coordinate of the piece, that has to be cleared
     */
    public void updatePieceLocation(ChessPiece chessPiece, int oldXCoordinate, int oldYCoordinate) {
        int newXCoordinate = chessPiece.getXCoordinate();
        int newYCoordinate = chessPiece.getYCoordinate();

        pieces[oldXCoordinate][oldYCoordinate] = null;

        pieces[newXCoordinate][newYCoordinate] = chessPiece;
    }

    private boolean hasValidCoordinates(int xCoordinate, int yCoordinate) {
        return isLegalBoardPosition(xCoordinate, yCoordinate);
    }

    private boolean isPositionEmpty(ChessBoard chessBoard, int xCoordinate, int yCoordinate) {
        return positionIsEmpty(chessBoard, xCoordinate, yCoordinate);
    }

    private boolean isColorMatch(ChessPiece chessPiece, PieceColor pieceColor) {
        return chessPiece.getPieceColor().equals(pieceColor);
    }

    private boolean startsInLegalPosition(ChessPiece chessPiece, int xCoordinate) {
        return chessPiece.startsInLegalPosition(xCoordinate);
    }
}
