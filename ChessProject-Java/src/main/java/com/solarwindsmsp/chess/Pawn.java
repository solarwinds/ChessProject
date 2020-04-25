package com.solarwindsmsp.chess;

public class Pawn extends Piece implements IPiece {

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("%sCurrent X: %dCurrent Y: %dPiece Color: %s", eol, this.getXCoordinate(), this.getYCoordinate(), this.getPieceColor());
    }

    public boolean allowMoveTo(int newX, int newY) {
        if(this.getPieceColor() == PieceColor.BLACK) {
            return checkBlackMove(newX, newY);
        } else {
            return checkWhiteMove(newX, newY);
        }

    }

    private boolean checkWhiteMove(int newX, int newY) {
        final int yDistanceMoved = newY - getYCoordinate();

        final boolean newyOK = getYCoordinate() == 1 ?
                yDistanceMoved == 1 || yDistanceMoved == 2 :
                yDistanceMoved == 1;
        final boolean newxOK = getXCoordinate() == newX;

        return newxOK && newyOK;
    }

    private boolean checkBlackMove(int newX, int newY) {

        final int yDistanceMoved = getYCoordinate() - newY;
        final boolean newyOK = getYCoordinate() == ChessBoard.MAX_BOARD_HEIGHT - 1 ?
                yDistanceMoved == 1 || yDistanceMoved == 2 :
                yDistanceMoved == 1;
        final boolean newxOK = getXCoordinate() == newX;

        return newxOK && newyOK;
    }


    public boolean allowCaptureAt(int newX, int newY) {
        throw new UnsupportedOperationException("Need to implement Pawn.allowCaptureAt");
    }


}
