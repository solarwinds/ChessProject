package com.solarwindsmsp.chess;

import java.util.HashMap;
import java.util.Map;

import static com.solarwindsmsp.chess.PieceColor.BLACK;
import static com.solarwindsmsp.chess.PieceColor.WHITE;

public class MovementStrategyFactory {

    private static final Map<String, MovementStrategy> strategyMap = new HashMap();

    static {
        strategyMap.put(getKey(Pawn.class, BLACK), new BlackPawnMovementStrategy());
        strategyMap.put(getKey(Pawn.class, WHITE), new WhitePawnMovementStrategy());
    }

    public MovementStrategy getMovementStrategy(Piece piece) {
        return strategyMap.get(getKey(piece.getClass(), piece.getColor()));
    }

    private static String getKey(Class clazz, PieceColor color) {
        return "" + clazz.getSimpleName() + color;
    }
}
