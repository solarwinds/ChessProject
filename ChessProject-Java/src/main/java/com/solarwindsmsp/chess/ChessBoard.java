package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.exceptions.UnknownColorException;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    // should define the rules for the beginning of the game
    public static int MAX_NUMBER_OF_PAWNS = 8;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void movePiece(Pawn pawn, int newXCoordinate, int newYCoordinate) {
        pieces[pawn.getXCoordinate()][pawn.getYCoordinate()] = null;
        pieces[newXCoordinate][newYCoordinate] = pawn;
    }

    /**
     * @param newPiece = the new piece that will be placed at the new coordinates
     * @param xCoordinate = X coordinate where the piece that will be capture is placed
     * @param yCoordinate = Y coordinate where the piece that will be capture is placed
     */
    public void capturePiece(Pawn newPiece, int xCoordinate, int yCoordinate) {
        pieces[xCoordinate][yCoordinate].invalidatePiece();
        pieces[xCoordinate][yCoordinate] = newPiece;
    }

    public Pawn getPiece(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor, boolean startingPosition) {

        if (getNumberOfPawns(pieceColor) == MAX_NUMBER_OF_PAWNS) {

        }

        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        pawn.setPieceColor(pieceColor);
        if (startingPosition) {
            try {
                if (!pawn.isLegalStartingPosition()) {
                    pawn.invalidatePiece();
                    return;
                }
            } catch (UnknownColorException e) {
                System.out.println("Failed to add piece because it contains an unknown color. " + e.getMessage());
                pawn.invalidatePiece();
                return;
            }
        } else {
            if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
                pawn.invalidatePiece();
                return;
            }
        }
        if (pieces[xCoordinate][yCoordinate] != null) {
            pawn.invalidatePiece();
            return;
        }
        pawn.setChessBoard(this);
        pieces[xCoordinate][yCoordinate] = pawn;

    }

    public int getNumberOfPawns(PieceColor pieceColor) {
        int numberOfPawns = 0;
        for (int i = 0; i < MAX_BOARD_HEIGHT; i ++) {
            for (int j = 0; j < MAX_BOARD_WIDTH; j++) {
                if (pieces[i][j] != null && pieces[i][j].getPieceColor() == pieceColor) {
                    numberOfPawns++;
                }
            }
        }
        return numberOfPawns;
    }


    public static boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate <= 7 && yCoordinate >= 0 && yCoordinate <=7;
    }

    public void printBoard() {
        for (int i = 0; i < MAX_BOARD_HEIGHT; i ++) {
            for (int j = 0; j < MAX_BOARD_WIDTH; j++) {
                if (pieces[i][j] == null) {
                    System.out.print("|-----|");
                } else {
                    System.out.print("|" + pieces[i][j].getPieceColor() + "|");
                }
            }
            System.out.println("\n");
        }
    }
}
