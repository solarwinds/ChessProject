package com.solarwindsmsp.chess.validators;

import com.solarwindsmsp.chess.enums.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.solarwindsmsp.chess.enums.PieceType.PAWN;

public class MovementValidatorFactory {

    final static Map<PieceType, Supplier<MovementValidator>> map = new HashMap<>();

    static {
        map.put(PAWN, PawnMovementValidator::new);
    }

    public static MovementValidator getValidator(PieceType pieceType) {
        Supplier<MovementValidator> validator = map.get(pieceType);
        if (Objects.nonNull(validator)) {
            return validator.get();
        }
        throw new IllegalArgumentException("No such validator for type " + pieceType);
    }

}
