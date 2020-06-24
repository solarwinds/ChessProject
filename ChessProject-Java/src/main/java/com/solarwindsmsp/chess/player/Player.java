package com.solarwindsmsp.chess.player;

import com.solarwindsmsp.chess.piece.PieceColor;

public class Player {
  private final String name;
  private final PieceColor pieceColor;

  public Player(String name, PieceColor pieceColor){
    this.name = name;
    this.pieceColor = pieceColor;
  }

  public String getName() {
    return name;
  }

  public PieceColor getPieceColor() {
    return pieceColor;
  }
}
