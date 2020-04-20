package com.solarwindsmsp.chess.movement;

import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Piece;

public class SimpleMove extends Move{

    public SimpleMove(ChessBoard chessBoard, Piece piece, Position startPosition, Position endPosition) {
        super(piece, chessBoard, startPosition, endPosition);
    }

    @Override
    public MovementType getType() {
        return MovementType.MOVE;
    }

    @Override
    public void perform() {
        startPosition.setPiece(null);
        endPosition.setPiece(piece);
        piece.setPosition(endPosition);
    }
}
