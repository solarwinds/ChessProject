<?php

namespace SolarWinds\Chess;

class ChessBoard
{
    const BOARD_WIDTH = 8;
    const BOARD_HEIGHT = 8;

    private $cells;

    public function __construct()
    {
        $this->cells = array_fill(0, self::BOARD_WIDTH, array_fill(0, self::BOARD_HEIGHT, 0));
    }

    public function add(Pawn $pawn, $xCoordinate, $yCoordinate, PieceColorEnum $pieceColor)
    {
        throw new \ErrorException("Need to implement " . __METHOD__);
    }

    /**
 	 * @return boolean
 	 **/
    public function isLegalBoardPosition($xCoordinate, $yCoordinate)
    {
        // NOTE: doing this is better than checking if they are between 0 and BOARD_*, since it technically supports different board configurations, and since it actually checks if that cell really exists, which is the point
        return isset($this->cells[$xCoordinate][$yCoordinate]);
    }
    
    public function getCells () {
        return $this->cells; // NOTE: returns a copy
    }
}
