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

    public function __construct(PieceColorEnum $pieceColorEnum)
    {
        $this->xCoordinate = static::INVALID;
        $this->yCoordinate = static::INVALID;
        $this->pieceColorEnum = $pieceColorEnum;
    }

    public function getChesssBoard()
    {
        return $this->chessBoard;
    }

    public function setChessBoard(ChessBoard $chessBoard)
    {
        $this->chessBoard = $chessBoard;
    }

    /** @return int */
    public function getXCoordinate()
    {
        return $this->xCoordinate;
    }

    /** @var int */
    public function setXCoordinate($value)
    {
        $this->xCoordinate = $value;
    }

    /** @return int */
    public function getYCoordinate()
    {
        return $this->yCoordinate;
    }

    /** @var int */
    public function setYCoordinate($value)
    {
        $this->yCoordinate = $value;
    }

    public function getPieceColor()
    {
        return $this->pieceColorEnum;
    }

    public function setPieceColor(PieceColorEnum $value)
    {
        $this->pieceColorEnum = $value;
    }

    public function move(MovementTypeEnum $movementTypeEnum, $newX, $newY)
    {
        throw new \Exception("Need to implement " . __METHOD__);
    }

    public function toString()
    {
		return "x({$this->xCoordinate}), y({$this->yCoordinate}), pieceColor({$this->pieceColorEnum})";
    }
}

