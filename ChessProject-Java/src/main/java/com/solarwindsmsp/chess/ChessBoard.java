package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.enums.PieceType;
import com.solarwindsmsp.chess.model.Pawn;
import com.solarwindsmsp.chess.model.Piece;
import com.solarwindsmsp.chess.util.Consts;

public class ChessBoard {

    private Piece[][] pieces;

    public ChessBoard() {
        pieces = new Piece[Consts.MAX_PAWN_NUMBER][Consts.MAX_PAWN_NUMBER];
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (isLegalBoardPosition(xCoordinate, yCoordinate)) {
            int numberOfSamePiece = numberOfPiecesByTypeAndColour(piece);
            if (numberOfSamePiece > piece.getPieceType().getMaxAllowedPieces()) {
                System.out.println("throw some error here or whatever is required by test class");
            }
            //START check for piece on destination cell when adding
            if (pieces[xCoordinate][yCoordinate] != null) {
                setPieceOutsideBoard(piece);
            } else {
                // START check for correct initial row
                if (!isLegalColourPosition(piece.getPieceColor(), piece.getPieceType(), yCoordinate)) {
                    setPieceOutsideBoard(piece);
                }
                // END check for correct initial row

                piece.setXCoordinate(xCoordinate);
                piece.setYCoordinate(yCoordinate);
                pieces[xCoordinate][yCoordinate] = piece;
            }
            // END check for piece on destination cell when adding


        }
        System.out.println("Wrong coordinates!!! Implementing check here in case input data is not validated before this method call");
        setPieceOutsideBoard(piece);
    }

    public boolean isLegalColourPosition(PieceColor pieceColor, PieceType pieceType, int yCoordinate) {
        if (PieceColor.WHITE.equals(pieceColor) && yCoordinate > 1) {
            return false;
        } else if (yCoordinate < 6) {
            return false;
        }
        return true;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= Consts.MIN_BOARD_WIDTH && xCoordinate <= Consts.MAX_BOARD_WIDTH && yCoordinate >= Consts.MIN_BOARD_HEIGHT && yCoordinate <= Consts.MAX_BOARD_HEIGHT) {
            return true;
        }
        return false;
    }

    private int numberOfPiecesByTypeAndColour(Piece piece) {
        int numberOfPieces = 0;
        for (int i = 0; i <= Consts.MAX_BOARD_HEIGHT; i++) {
            for (int j = 0; j <= Consts.MAX_BOARD_WIDTH; j++) {
                if (isSameTypeAndColour(piece, pieces[i][j])) {
                    numberOfPieces++;
                }
            }
        }
        return numberOfPieces;
    }

    private boolean isSameTypeAndColour(Piece piece, Piece piece1) {
        return piece1 != null && (piece1.getPieceType().equals(piece.getPieceType()) && piece1.getPieceColor().equals(piece.getPieceColor()));
    }

    private void setPieceOutsideBoard(Piece piece) {
        piece.setXCoordinate(Consts.DEFAULT_OUTSIDE_BOARD_COORDINATE);
        piece.setYCoordinate(Consts.DEFAULT_OUTSIDE_BOARD_COORDINATE);
    }
}
