package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.movement.Move;
import com.solarwindsmsp.chess.movement.MovementType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pawn extends Piece {

    public Pawn(ChessBoard chessBoard, PieceColor pieceColor) {
        super(chessBoard, pieceColor);
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();

        if(pieceColor == PieceColor.WHITE) {
            if(position.top() != null && position.top().getPiece() == null ) {
                moves.add(new Move(MovementType.MOVE, position, position.top()));
            }

            Position topRight = position.topRight();
            if(topRight != null && topRight.getPiece() != null && topRight.getPiece().getPieceColor() != pieceColor) {
                moves.add(new Move(MovementType.CAPTURE, position, position.topRight()));
            }

            Position topLeft = position.topLeft();
            if(topLeft != null && topLeft.getPiece() != null && topLeft.getPiece().getPieceColor() != pieceColor) {
                moves.add(new Move(MovementType.CAPTURE, position, position.topLeft()));
            }
        } else {
            if(position.bottom() != null && position.bottom().getPiece() == null ) {
                moves.add(new Move(MovementType.MOVE, position, position.bottom()));
            }

            Position bottomLeft = position.bottomLeft();
            if(bottomLeft != null && bottomLeft.getPiece() != null && bottomLeft.getPiece().getPieceColor() != pieceColor) {
                moves.add(new Move(MovementType.CAPTURE, position, position.bottomLeft()));
            }

            Position bottomRight = position.bottomRight();
            if(bottomRight != null && bottomRight.getPiece() != null && bottomRight.getPiece().getPieceColor() != pieceColor) {
                moves.add(new Move(MovementType.CAPTURE, position, position.bottomRight()));
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return getCurrentPositionAsString();
    }

    protected String getCurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, position.getX(), position.getY(), pieceColor);
    }
}
