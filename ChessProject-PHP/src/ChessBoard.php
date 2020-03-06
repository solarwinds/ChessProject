<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\Exception\IllegalPosition;
use SolarWinds\Chess\Exceptions\InvalidMove;
use SolarWinds\Chess\Pieces\BasePiece;
use SolarWinds\Chess\Pieces\PieceColorEnum;
use function Couchbase\defaultDecoder;

class ChessBoard
{
    const MAX_BOARD_WIDTH = 8;
    const MAX_BOARD_HEIGHT = 8;
    const MIN_BOARD_POSITION = 0;
    const REMOVE_FROM_BOARD_POSITION = -1;

    private $pieces;

    public function __construct()
    {
        $this->pieces = array_fill(0, self::MAX_BOARD_WIDTH, array_fill(0, self::MAX_BOARD_HEIGHT, 0));
    }

    /**
     * @param $piece
     * @param $xCoordinate
     * @param $yCoordinate
     * @param PieceColorEnum $pieceColor
     * @return bool
     */
    public function add(BasePiece $piece, $xCoordinate, $yCoordinate)
    {
        if (!$this->isLegalBoardPosition($xCoordinate, $yCoordinate)) {
            throw new InvalidMove($xCoordinate, $yCoordinate);
        }

        $pieceOnBoard = isset($this->pieces[$xCoordinate][$yCoordinate]) ? $this->pieces[$xCoordinate][$yCoordinate] : false;

        if (!is_numeric($pieceOnBoard)) {
            $piece->setXCoordinate(self::REMOVE_FROM_BOARD_POSITION)->setYCoordinate(self::REMOVE_FROM_BOARD_POSITION);
            return false;
        }

        $this->pieces[$xCoordinate][$yCoordinate] = $piece->setXCoordinate($xCoordinate)
            ->setYCoordinate($yCoordinate)
            ->setChessBoard($this);
        return true;
    }

    /**
     * @param int $xCoordinate
     * @param int $yCoordinate
     * @return bool
     */
    public function isLegalBoardPosition(int $xCoordinate, int $yCoordinate): bool
    {
        $x = ($xCoordinate >= self::MIN_BOARD_POSITION && $xCoordinate <= self::MAX_BOARD_WIDTH);
        $y = ($yCoordinate >= self::MIN_BOARD_POSITION && $yCoordinate <= self::MAX_BOARD_HEIGHT);

        return ($x && $y);

    }
}
