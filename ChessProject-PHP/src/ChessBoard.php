<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\Exception\IllegalPosition;
use SolarWinds\Chess\Pieces\PieceInterface;

class ChessBoard
{
    const MAX_BOARD_WIDTH = 8;
    const MAX_BOARD_HEIGHT = 8;
    const START_BOARD_INDEX = 0;
    const DEFAULT_BOARD_VALUE = 0;

    private $pieces;

    /**
     * ChessBoard constructor.
     */
    public function __construct()
    {
        $this->pieces = array_fill(
            self::START_BOARD_INDEX,
            self::MAX_BOARD_WIDTH,
            array_fill(
                self::START_BOARD_INDEX,
                self::MAX_BOARD_HEIGHT,
                self::DEFAULT_BOARD_VALUE
            )
        );
    }

    /**
     * @param PieceInterface $pieceToAdd
     * @param int $xCoordinate
     * @param int $yCoordinate
     * @return bool
     */
    public function add(PieceInterface $pieceToAdd, int $xCoordinate, int $yCoordinate): bool
    {
        if (!$this->isLegalBoardPosition($xCoordinate, $yCoordinate)) {
            throw new IllegalPosition($xCoordinate, $yCoordinate);
        }

        $pieceOnBoard = $this->pieces[$xCoordinate][$yCoordinate];
        if ($pieceOnBoard instanceof PieceInterface) {
            $pieceToAdd->setXCoordinate(-1)->setYCoordinate(-1);
            return false;
        }

        $this->pieces[$xCoordinate][$yCoordinate] = $pieceToAdd->setXCoordinate($xCoordinate)
            ->setYCoordinate($yCoordinate)
            ->setChessBoard($this);
        return true;
    }

    /**
     * @param int $xCoordinate
     * @param int $yCoordinate
     * @return boolean
     */
    public function isLegalBoardPosition(int $xCoordinate, int $yCoordinate): bool
    {
        return ($xCoordinate >= self::START_BOARD_INDEX && $xCoordinate < self::MAX_BOARD_WIDTH)
            && ($yCoordinate >= self::START_BOARD_INDEX && $yCoordinate < self::MAX_BOARD_HEIGHT);
    }
}
