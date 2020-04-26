package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.model.enums.ValidMove;
import com.solarwindsmsp.chess.util.Constants;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessBoard {

    private ChessPiece[][] pieces;

    public ChessBoard(ChessPiece[][] pieces) {
        this.pieces = pieces;
    }

    public ChessBoard() {
        pieces = new ChessPiece[Constants.MAX_BOARD_WIDTH][Constants.MAX_BOARD_HEIGHT];
    }

    public ChessPiece[][] getPieces() {
        return pieces;
    }

    public void addPiece(ChessPiece chessPiece, int xCoordinate, int yCoordinate) {
        if (isLegalMove(xCoordinate, yCoordinate) && pieces[xCoordinate][yCoordinate] == null && isNotMaxNumberOfSamePiece(chessPiece)) {
            pieces[xCoordinate][yCoordinate] = chessPiece;
            chessPiece.setxCoordinate(xCoordinate);
            chessPiece.setyCoordinate(yCoordinate);
        } else {
            chessPiece.setxCoordinate(-1);
            chessPiece.setyCoordinate(-1);

        }
    }

    private boolean isNotMaxNumberOfSamePiece(ChessPiece chessPiece) {
        return Arrays.stream(pieces)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.getPieceType() == chessPiece.getPieceType()).count() < chessPiece.getPieceType().getMaxNumberOfPiece();
    }

    public void movePiece(ChessPiece chessPiece, int newXLocation, int newYLocation) {
        if (isLegalMove(newXLocation, newYLocation) && isLegalChessPieceMove(chessPiece, newXLocation, newYLocation)) {
            pieces[newXLocation][newYLocation] = chessPiece;
            pieces[chessPiece.getxCoordinate()][chessPiece.getyCoordinate()] = null;
            chessPiece.setxCoordinate(newXLocation);
            chessPiece.setyCoordinate(newYLocation);

        }
    }

    private boolean isLegalMove(int newXLocation, int newYLocation) {
        if (isLegalBoardPosition(newXLocation, newYLocation) && this.getPieces()[newXLocation][newYLocation] == null) {
            return true;
        }
        return false;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        if (xCoordinate > Constants.MAX_BOARD_HEIGHT || xCoordinate < 0 || yCoordinate > Constants.MAX_BOARD_WIDTH || yCoordinate < 0) {
            return false;
        }
        return true;
    }

    private boolean isLegalChessPieceMove(ChessPiece chessPiece, int newXLocation, int newYLocation) {
        if (chessPiece.getPieceType().getValidMove() == ValidMove.FORWARD && newXLocation == chessPiece.getxCoordinate()) {
            switch (chessPiece.getPieceColor()) {
                case WHITE:
                    if (chessPiece.getyCoordinate() - newYLocation == chessPiece.getPieceType().getValidMoveHeightSize()) {
                        return true;
                    }
                    break;
                case BLACK:
                    if (newYLocation - chessPiece.getyCoordinate() == chessPiece.getPieceType().getValidMoveHeightSize()) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
}
