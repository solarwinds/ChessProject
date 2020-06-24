package com.solarwindsmsp.chess.exception;

/**
 * Exception thrown when an invalid move is made
 */
public class UnsupportedMoveException extends RuntimeException {

  private int errorCode;

  public UnsupportedMoveException(int errorCode, String message){
    super(message);
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }
}
