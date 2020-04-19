package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Piece;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Position {

    private final int x;
    private final int y;
    private final ChessBoard board;

    @Setter
    private Piece piece;

    public Position top() {
        return board.getPosition(x, y + 1);
    }

    public Position bottom() {
        return board.getPosition(x, y - 1);
    }

    public Position left() {
        return board.getPosition(x - 1, y);
    }

    public Position right() {
        return board.getPosition(x + 1, y);
    }

    public Position topRight() {
        return board.getPosition(x + 1, y + 1);
    }

    public Position topLeft() {
        return board.getPosition(x - 1, y + 1);
    }

    public Position bottomLeft() {
        return board.getPosition(x - 1, y - 1);
    }

    public Position bottomRight() {
        return board.getPosition(x + 1, y - 1);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", piece=" + piece +
                '}';
    }
}
