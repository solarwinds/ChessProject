package com.solarwindsmsp.chess.strategy;

import com.solarwindsmsp.chess.board.Cell;

public interface BoardInitializationStrategy {

  /**
   * Used to initialize all pieces on a chess board. The board can have different
   * dimensions based on implementing strategy
   *
   * @return fully initialized chess board
   */
  Cell[][] initializeBoard();

  /**
   * Returns the width of the board for current strategy
   * @return width
   */
  int getBoardWidth();

  /**
   * Returns the height of the board for current strategy
   * @return width
   */
  int getBoardHeight();
}
