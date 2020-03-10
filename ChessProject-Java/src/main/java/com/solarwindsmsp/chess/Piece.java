package com.solarwindsmsp.chess;

/**
 * Superclass that contains logic relevant to all pieces on a chess board.
 */
public abstract class Piece {

    protected ChessBoard chessBoard;
    protected PieceColor pieceColor;
    protected int xCoordinate;
    protected int yCoordinate;


    /**
     * Constructor for a piece, assuming a piece cannot exist without having a specified position on the board.
     *
     * @param pieceColor  of the piece
     * @param xCoordinate position along the horizontal axis of the board
     * @param yCoordinate position along the vertical axis of the board
     * @param chessBoard  current chess board
     */
    public Piece(final PieceColor pieceColor, final int xCoordinate, final int yCoordinate,
                 final ChessBoard chessBoard) {

        this.pieceColor = pieceColor;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.chessBoard = chessBoard;
    }

    /**
     * Get the x coordinate of a piece.
     *
     * @return x coordinate
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Get the y coordinate of a piece.
     *
     * @return y coordinate
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Get the piece colour.
     *
     * @return piece colour
     */
    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    /**
     * Abstract move() that is implemented by all pieces.
     *
     * @param movementType chosen movement type for a piece
     * @param newX         chosen horizontal position for the piece to move to
     * @param newY         chosen vertical position for the piece to move to
     */
    public abstract boolean move(MovementType movementType, int newX, int newY) throws Exception;

    /**
     * Abstract toString() that is implemented by all pieces.
     *
     * @return individual character of a piece
     */
    public abstract String toString();

    /**
     * Abstract pieceName() that is implemented by all pieces.
     *
     * @return piece name
     */
    public abstract String pieceName();

    /**
     * Abstract pieceColor() that is implemented by all pieces.
     *
     * @return piece colour
     */
    public abstract String pieceColor();

    /**
     * Handy for debugging purposes in order to see the piece positions.
     *
     * @return current position of a piece
     */
    protected String currentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate,
                pieceColor);
    }

    /**
     * Update the coordinates of a piece contained in the map.
     *
     * @param newX new x coordinate
     * @param newY new y coordinate
     */
    protected void updateCoords(int newX, int newY) {
        chessBoard.updateMap(xCoordinate, yCoordinate, newX, newY);
        xCoordinate = newX;
        yCoordinate = newY;
    }

    /**
     * Abstract boolean to check whether the maximum number of a given piece on a board has been exceeded.
     *
     * @return whether the maximum numbas has been exceeded
     */
    protected abstract boolean isMaxNumberExceeded();
}
