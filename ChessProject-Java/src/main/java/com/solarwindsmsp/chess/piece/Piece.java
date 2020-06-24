package com.solarwindsmsp.chess.piece;

/**
 * Class which encapsulates common fields and methods for all chess type of pieces
 */
public abstract class Piece {
  private final PieceColor pieceColor;

  public Piece(PieceColor pieceColor){
    this.pieceColor = pieceColor;
  }

  public PieceColor getPieceColor(){
    return pieceColor;
  }


  /**
   * Defines a general rule for each type of piece which tells if
   * this particular piece can be moved from a starting point to another point
   *
   * @param startX coordinate
   * @param startY coordinate
   * @param endX coordinate
   * @param endY coordinate
   * @return true if can be moved, false otherwise
   */
  public abstract boolean canMove(int startX, int startY, int endX, int endY);

  @Override
  public String toString() {
    return getCurrentPositionAsString();
  }

  protected String getCurrentPositionAsString() {
    return String.format("Piece Color: %s, Piece Type: %s", pieceColor, getClass().getName());
  }
}
