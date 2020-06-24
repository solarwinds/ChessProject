package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.Cell;
import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.exception.UnsupportedMoveException;
import com.solarwindsmsp.chess.piece.Piece;
import com.solarwindsmsp.chess.player.Player;
import com.solarwindsmsp.chess.strategy.TraditionalInitializationStrategy;

/**
 * Class which acts as a main controller of a chess game
 */
public class ChessGame {
  private final ChessBoard chessBoard;
  private final Player whitePlayer;
  private final Player blackPlayer;
  private Player currentPlayer;

  public ChessGame(Player whitePlayer, Player blackPlayer){
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    currentPlayer = whitePlayer;
    chessBoard = new ChessBoard(new TraditionalInitializationStrategy());
  }

  /**
   * Moves a piece of a particular player from one cell to another
   * @param player player which moves the piece
   * @param startX position
   * @param startY position
   * @param endX position
   * @param endY position
   */
  public void movePiece(Player player, int startX, int startY, int endX, int endY){
    Cell start = chessBoard.getCell(startX, startY);
    Cell end = chessBoard.getCell(endX, endY);
    Piece pieceToMove = start.getPiece();

    validateMove(player, pieceToMove, start, end);

    start.setPiece(null);
    end.setPiece(pieceToMove);
    switchPlayerTurn(player);
  }

  /**
   * Validates a move from one cell to another of a particular piece, throws an
   * @code UnsupportedMoveException in following cases:
   *  - there is no piece in start cell
   *  - it's not player's turn to move
   *  - player tries to move a piece of different color
   *  - start or end cells are outside board boundaries
   *  -
   * @param player current player
   * @param pieceToMove piece which should be moved
   * @param start cell
   * @param end cell
   */
  private void validateMove(Player player, Piece pieceToMove, Cell start, Cell end){
    if(pieceToMove == null ||
        end.getPiece() != null ||
        player != currentPlayer ||
        player.getPieceColor() != pieceToMove.getPieceColor() ||
        !chessBoard.isLegalBoardPosition(start.getxCoordinate(), start.getxCoordinate()) ||
        !chessBoard.isLegalBoardPosition(end.getxCoordinate(), end.getyCoordinate()) ||
        !pieceToMove.canMove(start.getxCoordinate(), start.getyCoordinate(), end.getxCoordinate(), end.getyCoordinate())){
      throw new UnsupportedMoveException(0, String.format("Invalid move from %s to %s for player %s", start, end, player.getName()));
    }
  }

  private void switchPlayerTurn(Player player){
    if(player != whitePlayer && player != blackPlayer){
      throw new IllegalArgumentException(String.format("Game isn't aware of player %s", player.getName()));
    }
    currentPlayer = player == whitePlayer ? blackPlayer : whitePlayer;
  }

  public ChessBoard getChessBoard() {
    return chessBoard;
  }
}
