package com.solarwindsmsp.chess.model.enums;

import com.solarwindsmsp.chess.util.Constants;

public enum PieceType {

    PAWN("Pawn", Constants.MAX_NUMBER_OF_PAWNS, 1, 0, ValidMove.FORWARD);

    private String pieceName;

    private int maxNumberOfPiece;

    private int validMoveHeightSize;

    private int validMoveWidthSize;

    private  ValidMove validMove;

    PieceType(String pieceName, int maxNumberOfPiece, int validMoveHeightSize, int validMoveWeightSize, ValidMove validMove) {
        this.pieceName = pieceName;
        this.maxNumberOfPiece = maxNumberOfPiece;
        this.validMoveHeightSize = validMoveHeightSize;
        this.validMoveWidthSize = validMoveWeightSize;
        this.validMove = validMove;
    }

    public ValidMove getValidMove() {
        return validMove;
    }

    public int getValidMoveHeightSize() {
        return validMoveHeightSize;
    }

    public int getValidMoveWeightSize() {
        return validMoveWidthSize;
    }

    public String getPieceName() {
        return pieceName;
    }

    public int getMaxNumberOfPiece() {
        return maxNumberOfPiece;
    }
}
