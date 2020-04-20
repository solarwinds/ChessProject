package com.solarwindsmsp.chess.movement;

import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Piece;

public class CaptureMove extends Move{

    public CaptureMove(ChessBoard chessBoard, Piece piece, Position startPosition, Position endPosition) {
        super(piece, chessBoard, startPosition, endPosition);
    }

    @Override
    public MovementType getType() {
        return MovementType.CAPTURE;
    }

    @Override
    public void perform() {
        Piece pieceToRemove = endPosition.getPiece();

        chessBoard.capture(pieceToRemove);

        startPosition.setPiece(null);
        endPosition.setPiece(piece);
        piece.setPosition(endPosition);
    }
}
