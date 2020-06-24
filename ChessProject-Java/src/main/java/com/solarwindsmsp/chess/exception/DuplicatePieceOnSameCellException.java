package com.solarwindsmsp.chess.exception;

/**
 * Exception thrown when trying to add a piece on a cell which already contains another piece
 */
public class DuplicatePieceOnSameCellException extends RuntimeException{
  public DuplicatePieceOnSameCellException(String message){
    super(message);
  }
}
