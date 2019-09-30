package com.solarwindsmsp.chess;

public abstract class ChessPiece {

    // TODO: Consider if pieces need to store their co-ords at all:
    // information is redundant as encoded in their position on board.
    // Reference to ChessBoard has been removed to avoid circular dependency on containing type.    

    private int xCoordinate = -1;  // Initialise to invalid value
    private int yCoordinate = -1;
    private PieceColor pieceColor;

    public ChessPiece(PieceColor value) {
        super();
        this.pieceColor = value;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected int toRelativeLeftSquares(int newX) {
        // Num squares to the left from point of view of black or white
        if (pieceColor == PieceColor.BLACK) {
            return newX - getXCoordinate();
        } else {
            return getXCoordinate() - newX;
        }
    }

    protected int toRelativeForwardSquares(int newY) {
        // num squares forward from point of view of black or white
        if (pieceColor == PieceColor.BLACK) {
            return getYCoordinate() - newY;
        } else {
            return newY - getYCoordinate();
        }
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        // Fix formatting for Java String.format
        return String.format("Current X: %d%s,Current Y:%d%sPiece Color: %s%s", xCoordinate, eol, yCoordinate, eol, pieceColor, eol);
    }

    // Piece type-specific methods

    public abstract String toSymbol();

    public abstract boolean isValidMove(MovementType movementType, int newX, int newY);

    public abstract void Move(MovementType movementType, int newX, int newY);

    public abstract int getMaxPiecesPerColor();

}
