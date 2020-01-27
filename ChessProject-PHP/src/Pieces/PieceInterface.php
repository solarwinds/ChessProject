<?php


namespace SolarWinds\Chess\Pieces;


use SolarWinds\Chess\ChessBoard;
use SolarWinds\Chess\Util\MovementTypeEnum;
use SolarWinds\Chess\Util\PieceColorEnum;

interface PieceInterface
{
    /**
     * @return PieceColorEnum
     */
    public function getPieceColor(): PieceColorEnum;

    /**
     * @param int $value
     * @return PieceInterface
     */
    public function setXCoordinate(int $value): PieceInterface;

    /**
     * @return int
     */
    public function getXCoordinate(): int;

    /**
     * @param int $value
     * @return PieceInterface
     */
    public function setYCoordinate(int $value): PieceInterface;

    /**
     * @return int
     */
    public function getYCoordinate(): int;

    /**
     * @param ChessBoard $chessBoard
     * @return PieceInterface
     */
    public function setChessBoard(ChessBoard $chessBoard): PieceInterface;

    /**
     * @return ChessBoard
     */
    public function getChessBoard(): ChessBoard;

    /**
     * @param MovementTypeEnum $movementTypeEnum
     * @param $newX
     * @param $newY
     */
    public function move(MovementTypeEnum $movementTypeEnum, $newX, $newY): void;
} 