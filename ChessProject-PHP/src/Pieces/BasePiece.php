<?php


namespace SolarWinds\Chess\Pieces;


use SolarWinds\Chess\ChessBoard;


abstract class BasePiece
{
    /** @var PieceColorEnum */
    protected $pieceColorEnum;

    /** @var ChessBoard */
    protected $chessBoard;

    /** @var int */
    protected $xCoordinate;

    /** @var int */
    protected $yCoordinate;

    const LEGAL_DISTANCE_MOVES = [
        PieceColorEnum::WHITE => [1, 2],
        PieceColorEnum::BLACK => [-1, -2]
    ];
    /**
     * @return PieceColorEnum
     */
    public function getPieceColor(): PieceColorEnum
    {
        return $this->pieceColorEnum;
    }

    /**
     * @param int $value
     * @return $this
     */
    public function setXCoordinate(int $value)
    {
        $this->xCoordinate = $value;
        return $this;
    }

    /**
     * @return int
     */
    public function getXCoordinate(): int
    {
        return $this->xCoordinate;
    }

    /**
     * @param int $value
     * @return
     */
    public function setYCoordinate(int $value)
    {
        $this->yCoordinate = $value;
        return $this;
    }

    /**
     * @return int
     */
    public function getYCoordinate()
    {
        return $this->yCoordinate;
    }

    /**
     * @param ChessBoard $chessBoard
     * @return $this
     */
    public function setChessBoard(ChessBoard $chessBoard)
    {
        $this->chessBoard = $chessBoard;
        return $this;
    }

    /**
     * @return ChessBoard
     */
    public function getChessBoard(): ChessBoard
    {
        return $this->chessBoard;
    }

    public function setPieceColor(PieceColorEnum $value)
    {
        $this->pieceColorEnum = $value;
    }

    /**
     * @param MovementTypeEnum $movementTypeEnum
     * @param $newX
     * @param $newY
     */
    public function move(MovementTypeEnum $movementTypeEnum, int $newX, int $newY){}

    protected function getPawnDistanceMoves(): array
    {
        return self::LEGAL_DISTANCE_MOVES[(int)(string)$this->getPieceColor()];
    }

}