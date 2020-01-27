<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\Exceptions\imposibleCoordinates;
use SolarWinds\Chess\Pieces\PieceInterface;
use SolarWinds\Chess\Pieces\Pawn;

class ChessBoard
{
    const MAX_BOARD_WIDTH = 8;
    const MAX_BOARD_HEIGHT = 8;
    const BOARD_INDEX = 0;
    const BOARD_INDEX_DEFAULT = 0;

    private $pieces;

    public function __construct()
    {
        $this->pieces = array_fill(
            self::BOARD_INDEX, self::MAX_BOARD_WIDTH, 
            array_fill(self::BOARD_INDEX, self::MAX_BOARD_HEIGHT, self::BOARD_INDEX_DEFAULT)
        );
    }

    public function add(PieceInterface $pieceToAdd, int $xCoordinate, int $yCoordinate): bool
    {
        if (!$this->isLegalBoardPosition($xCoordinate, $yCoordinate)) {
            throw new imposibleCoordinates($xCoordinate, $yCoordinate);
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
    * @return boolean
    **/
    public function isLegalBoardPosition($xCoordinate, $yCoordinate)
    {
        if($xCoordinate < self::BOARD_INDEX || $xCoordinate > self::MAX_BOARD_WIDTH){
            return false;
        }
    
        if($yCoordinate < self::BOARD_INDEX || $yCoordinate > self::MAX_BOARD_WIDTH){
            return false;
        }
            
        return true;
    }
}
