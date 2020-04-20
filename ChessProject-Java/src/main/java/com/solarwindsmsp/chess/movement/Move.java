package com.solarwindsmsp.chess.movement;

import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.board.ChessBoard;
import com.solarwindsmsp.chess.piece.Piece;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public abstract class Move {
    protected final Piece piece;
    protected final ChessBoard chessBoard;
    protected final Position startPosition;
    protected final Position endPosition;

    public abstract MovementType getType();

    public abstract void perform();
}
