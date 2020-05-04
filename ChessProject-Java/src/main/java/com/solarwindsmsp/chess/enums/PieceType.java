package com.solarwindsmsp.chess.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PieceType {

    PAWN(8);

    private final int maxAllowed;

}
