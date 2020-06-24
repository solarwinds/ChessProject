package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.exception.DuplicatePieceOnSameCellException;
import com.solarwindsmsp.chess.piece.Piece;
import com.solarwindsmsp.chess.strategy.BoardInitializationStrategy;

/**
 * Class which represents a chess board. Encapsulates all the cells from a board with
 * their containing pieces
 */
public class ChessBoard {

    BoardInitializationStrategy initializationStrategy;

    private Cell[][] boardCells;

    public ChessBoard(BoardInitializationStrategy initializationStrategy) {
        this.initializationStrategy = initializationStrategy;
        boardCells = initializationStrategy.initializeBoard();
    }

    /**
     * This method shouldn't be used under normal circumstances!
     * All pieces should be added on board by initialization strategy
     * @param piece to be added
     * @param xCoordinate x coordinate
     * @param yCoordinate y coordinate
     */
    public void addPiece(Piece piece, int xCoordinate, int yCoordinate) {
        if(boardCells[xCoordinate][yCoordinate].getPiece() != null){
            throw new DuplicatePieceOnSameCellException(
                String.format("The cell at position %s %s already contains a piece", xCoordinate, yCoordinate));
        }
        boardCells[xCoordinate][yCoordinate].setPiece(piece);
    }

    /**
     * Verifies if a given position is legal under current initialization strategy
     * @param xCoordinate x coordinate
     * @param yCoordinate y coordinate
     * @return true if legal, false otherwise
     */
    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < initializationStrategy.getBoardWidth()
            && yCoordinate >= 0 && yCoordinate < initializationStrategy.getBoardHeight();
    }

    public Cell getCell(int x, int y){
        return boardCells[x][y];
    }

    public void setBoardCells(Cell[][] boardCells) {
        this.boardCells = boardCells;
    }
}
