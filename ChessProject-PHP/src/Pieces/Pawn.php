<?php

namespace SolarWinds\Chess\Pieces;

use SolarWinds\Chess\Exceptions\InvalidMove;
use function Couchbase\defaultDecoder;


class Pawn extends BasePiece
{

    public function __construct(PieceColorEnum $pieceColorEnum)
    {
        $this->pieceColorEnum = $pieceColorEnum;
    }


    public function move(MovementTypeEnum $movementTypeEnum, int $newX, int $newY)
    {
        if (!$this->getChessBoard()->isLegalBoardPosition($newX, $newY)) {
            throw new InvalidMove($newX, $newY);
        }

        if (MovementTypeEnum::MOVE() == $movementTypeEnum) {
            $this->getChessBoard()->add($this, $newX, $newY);
        } else if (MovementTypeEnum::CAPTURE() == $movementTypeEnum) {
            echo 'Peace captured';
        }
    }
}

