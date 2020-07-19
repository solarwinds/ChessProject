package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.enums.PieceType;
import com.solarwindsmsp.chess.model.Piece;
import com.solarwindsmsp.chess.util.Const;

public class ChessBoard {

    private Piece[][] pieces;

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public ChessBoard() {
        pieces = new Piece[Const.DEFAULT_BOARD_SIDE][Const.DEFAULT_BOARD_SIDE];
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (isLegalBoardPosition(xCoordinate, yCoordinate) && isLegalColourAndTypeInitialPosition(piece.getPieceColor(), piece.getPieceType(), xCoordinate)) {
            int numberOfSamePiece = numberOfPiecesByTypeAndColour(piece);
            if (numberOfSamePiece > piece.getPieceType().getMaxAllowedPieces()) {
                System.out.println("Max number of same piece has been reached");
            }
            if (isCellOccupied(xCoordinate, yCoordinate)) {
                setPieceOutsideBoard(piece);
                System.out.println("Another piece is already on this cell");
            } else {
                addPieceOnBoard(piece, xCoordinate, yCoordinate);
            }
        } else {
            setPieceOutsideBoard(piece);
            System.out.println("Wrong coordinates!!! Implementing check here in case input data is not validated before this method call");
        }


    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= Const.MIN_BOARD_WIDTH && xCoordinate <= Const.MAX_BOARD_WIDTH && yCoordinate >= Const.MIN_BOARD_HEIGHT && yCoordinate <= Const.MAX_BOARD_HEIGHT;
    }

    public boolean isCellOccupied(int xCoordinate, int yCoordinate) {
        return pieces[xCoordinate][yCoordinate] != null;
    }

    private void addPieceOnBoard(Piece piece, int xCoordinate, int yCoordinate) {
        piece.setXCoordinate(xCoordinate);
        piece.setYCoordinate(yCoordinate);
        pieces[xCoordinate][yCoordinate] = piece;
        piece.setChessBoard(this);
    }

    private boolean isLegalColourAndTypeInitialPosition(PieceColor pieceColor, PieceType pieceType, int xCoordinate) {
        switch (pieceType) {
            case KING:
                break;
            case QUEEN:
                break;
            case KNIGHT:
                break;
            case ROOK:
                break;
            case BISHOP:
                break;
            case PAWN:
                if (PieceColor.WHITE.equals(pieceColor)) {
                    return xCoordinate == 1;
                } else {
                    return xCoordinate == 6;
                }
            default:
                throw new IllegalStateException("Unexpected value: " + pieceType);

        }
        return false;
    }

    private int numberOfPiecesByTypeAndColour(Piece piece) {
        int numberOfPieces = 0;
        for (int i = 0; i <= Const.MAX_BOARD_HEIGHT; i++) {
            for (int j = 0; j <= Const.MAX_BOARD_WIDTH; j++) {
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
        piece.setXCoordinate(Const.DEFAULT_OUTSIDE_BOARD_COORDINATE);
        piece.setYCoordinate(Const.DEFAULT_OUTSIDE_BOARD_COORDINATE);
    }
}
