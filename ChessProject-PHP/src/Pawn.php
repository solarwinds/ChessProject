<?php

namespace SolarWinds\Chess;

class Pawn
{
    const INVALID = -1;
    
    /** @var PieceColorEnum */
    private $pieceColorEnum;
    
    /** @var ChessBoard */
    private $chessBoard;
    
    /** @var int */
    private $xCoordinate;
    
    /** @var int */
    private $yCoordinate;
    
    public function __construct (PieceColorEnum $pieceColorEnum) {
        $this->xCoordinate = static::INVALID;
        $this->yCoordinate = static::INVALID;
        $this->pieceColorEnum = $pieceColorEnum;
    }
    
    public function initialise (ChessBoard $chessBoard, int $xCoordinate, int $yCoordinate) {
        $this->chessBoard = $chessBoard;
        $this->xCoordinate = $xCoordinate;
        $this->yCoordinate = $yCoordinate;
    }
    
    /** @return int */
    public function getXCoordinate () {
        return $this->xCoordinate;
    }
    
    /** @return int */
    public function getYCoordinate () {
        return $this->yCoordinate;
    }
    
    public function getPieceColor () {
        return $this->pieceColorEnum;
    }
    
    public function move ($newX, $newY) {
        $this->chessBoard->isLegalBoardPosition($newX,$newY,TRUE);
        
        throw new \Exception("Need to implement " . __METHOD__);
    }
    
    public function toString () {
		return "x({$this->xCoordinate}), y({$this->yCoordinate}), pieceColor({$this->pieceColorEnum})";
    }
}
