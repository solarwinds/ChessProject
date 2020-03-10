package com.solarwindsmsp.chess;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Chess board class which contains the logic and pieces necessary for a game. The board can be set up in
 * a default way, or using custom settings.
 */
public class ChessBoard {

    private Map<Pair<Integer, Integer>, Piece> pieces;

    public static final int MAX_BOARD_WIDTH = 8;
    public static final int MAX_BOARD_HEIGHT = 8;
    public static final int WHITE_PAWN_RANK = 2;
    public static final int BLACK_PAWN_RANK = 7;

    public ChessBoard() {
        pieces = new HashMap<>();
    }

    /**
     * Add a piece to a given square on the chess board.
     *
     * @param piece chess piece to be added to the chess board
     */
    public boolean add(Piece piece) throws Exception {
        if (isLegalBoardPosition(piece.getXCoordinate(), piece.getYCoordinate())) {
            // the chosen square is on the board
            if (isSquareFree(piece.getXCoordinate(), piece.getYCoordinate())) {
                // the chosen square is not occupied by another piece
                if (!piece.isMaxNumberExceeded()) {
                    // the maximum number of the chosen piece has not been reached
                    pieces.put(new Pair<>(piece.getXCoordinate(), piece.getYCoordinate()), piece);
                    return true;
                } else {
                    throw new Exception("The maximum number of " + piece.pieceColor() + " " + piece.pieceName()
                            + " has been reached.");
                }
            } else {
                throw new Exception("The chosen square is already occupied.");
            }
        } else {
            throw new Exception(piece.pieceName() + " cannot be placed out of bounds of the board.");
        }
    }

    /**
     * Check if chosen square is within the range of the chess board.
     *
     * @param xCoordinate position of a given square along the horizontal axis
     * @param yCoordinate position of a given square along the vertical axis
     * @return whether a given square is within the range of the chess board
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return ((xCoordinate <= MAX_BOARD_WIDTH && xCoordinate > 0) && (yCoordinate <= MAX_BOARD_HEIGHT && yCoordinate > 0));
    }

    /**
     * Check if chosen square is occupied. This can be used in the future when capturing will be implemented.
     *
     * @return whether a given square on the chess board is free
     */
    public boolean isSquareFree(int xCoord, int yCoord) {
        return !(pieces.containsKey(new Pair<>(xCoord, yCoord)));

    }

    /**
     * Create a default chess setup. This can be expanded to include other pieces in the future.
     */
    public void setUpDefaultBoard() {
        pieces.clear();
        for (int i = 1; i <= 8; i++) {
            try {
                add(new Pawn(PieceColor.WHITE, i, WHITE_PAWN_RANK, this));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                add(new Pawn(PieceColor.BLACK, i, BLACK_PAWN_RANK, this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Visual aid for debugging purposes.
     */
    public void printChessBoard() {

        StringBuilder sb = new StringBuilder();

        for (int y = MAX_BOARD_HEIGHT; y >= 1; y--) {
            sb.append(y + " \t");
            for (int x = 1; x <= MAX_BOARD_WIDTH; x++) {
                if (pieces.containsKey(new Pair<>(x, y))) {
                    sb.append(pieces.get(new Pair<>(x, y)) + "  ");
                } else {
                    sb.append("-  ");
                }
            }
            sb.append("\n");
        }
        sb.append("    ");
        for (int x = 1; x <= MAX_BOARD_WIDTH; x++) {
            sb.append(x + "  ");
        }
        sb.append("\n\n\n");
        System.out.println(sb.toString());
    }

    /**
     * Move pieces on the chess board from their current position to a given new one.
     * @param currentX current position along the horizontal axis
     * @param currentY current position along the vertical axis
     * @param newX chosen x coordinate
     * @param newY chosen y coordinate
     * @param movementType specifies the piece to move
     * @return if piece was moved successfully
     */
    public boolean movePiece(int currentX, int currentY, int newX, int newY, MovementType movementType) {
        if (pieces.containsKey(new Pair<>(currentX, currentY))) {
            Piece selectedPiece = pieces.get(new Pair<>(currentX, currentY));
            try {
                return selectedPiece.move(movementType, newX, newY);
            } catch (Exception e) {
                System.out.println(selectedPiece.pieceName() + " cannot be moved.");
            }
        }
        return false;
    }

    /**
     * Update the map holding the pieces and their respective coordinates.
     * @param currentX current x coordinate
     * @param currentY current y coordinate
     * @param newX new x coordinate
     * @param newY new y coordinate
     */
    public void updateMap(int currentX, int currentY, int newX, int newY) {
        Piece selectedPiece = pieces.remove(new Pair<>(currentX, currentY));
        pieces.put(new Pair<>(newX, newY), selectedPiece);
    }

    /**
     * Helper method to be able to access the map inside the tests.
     * @return map with pieces and coordinates
     */
    protected Map<Pair<Integer, Integer>, Piece> getMapOfPieces() {
        return pieces;
    }

}

