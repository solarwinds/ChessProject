package com.solarwindsmsp.chess.strategy;

import com.solarwindsmsp.chess.board.Cell;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

/**
 * A 2000 years old initialization chess strategy originated from India. Each player begins
 * with 16 pieces: one king, one queen, two rooks, two knights, two bishops, and eight pawns.
 */
public class TraditionalInitializationStrategy implements BoardInitializationStrategy {

  private static final int BOARD_WIDTH = 8;
  private static final int BOARD_HEIGHT = 8;

  @Override
  public Cell[][] initializeBoard() {
    Cell[][] boardCells = initializeAllCells();
    initializeAllPieces(boardCells);
    return boardCells;
  }

  @Override
  public int getBoardWidth() {
    return BOARD_WIDTH;
  }

  @Override
  public int getBoardHeight() {
    return BOARD_HEIGHT;
  }

  private Cell[][] initializeAllCells() {
    Cell[][] boardCells = new Cell[BOARD_WIDTH][BOARD_HEIGHT];

    for(int i = 0; i < BOARD_WIDTH; i++){
      for(int j = 0; j < BOARD_HEIGHT; j++){
        boardCells[i][j] = new Cell(i, j, null);
      }
    }

    return boardCells;
  }

  private void initializeAllPieces(Cell[][] boardCells) {
    initializePawns(boardCells);
    //initializeKnights(boardCells);
    //initializeQueens(boardCells);
    //etc...
  }

  private void initializePawns(Cell[][] boardCells) {
    initializeWhitePawns(boardCells);
    initializeBlackPawns(boardCells);
  }

  private void initializeBlackPawns(Cell[][] boardCells) {
    int blackPawnRow = 1;
    for(int i = 0; i < BOARD_WIDTH; i++){
      boardCells[i][blackPawnRow].setPiece(new Pawn(PieceColor.BLACK));
    }
  }

  private void initializeWhitePawns(Cell[][] boardCells) {
    int whitePawnRow = 1;
    for(int i = 0; i < BOARD_WIDTH; i++){
      boardCells[i][whitePawnRow].setPiece(new Pawn(PieceColor.WHITE));
    }
  }
}
