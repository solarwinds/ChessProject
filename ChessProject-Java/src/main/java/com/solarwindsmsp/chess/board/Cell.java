package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.Piece;

public class Cell {

  private Piece piece;
  private final int xCoordinate;
  private final int yCoordinate;

  public Cell(int xCoordinate, int yCoordinate, Piece piece){
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.piece = piece;
  }

  public Piece getPiece() {
    return piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public int getxCoordinate() {
    return xCoordinate;
  }

  public int getyCoordinate() {
    return yCoordinate;
  }

  @Override
  public String toString() {
    return "Cell{" +
        "xCoordinate=" + xCoordinate +
        ", yCoordinate=" + yCoordinate +
        '}';
  }
}
