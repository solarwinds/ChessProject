package com.solarwindsmsp.chess.movement;

import com.solarwindsmsp.chess.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class Move {
    private final MovementType movementType;
    private final Position startPosition;
    private final Position endPosition;
}
