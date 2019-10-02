<?php

namespace SolarWinds\Chess\Domain\Piece\Contracts;

use SolarWinds\Chess\Domain\ChessBoard\ChessBoard;
use SolarWinds\Chess\Domain\Piece\MovementTypeEnum;
use SolarWinds\Chess\Domain\Piece\PieceColorEnum;

/**
 * Interface PieceInterface
 *
 * @package SolarWinds\Chess\Contracts
 */
interface PieceInterface
{
    /**
     * MUST return current board with all the pieces on it
     *
     * @return mixed
     */
    public function getChessBoard(): ChessBoard;

    /**
     * MUST set new chess board if there is update on it ( e.g. positionally )
     *
     * @param ChessBoard $chessBoard
     *
     * @return mixed
     */
    public function setChessBoard(ChessBoard $chessBoard);

    /**
     * @return int
     */
    public function getXCoordinate(): int;

    /**
     * MUST set X param which is associated with a row on the board
     *
     * @param int $value
     *
     * @return mixed
     */
    public function setXCoordinate(int $value);

    /**
     * @return int
     */
    public function getYCoordinate(): int;

    /**
     * MUST set X param which is associated with a column on the board
     *
     * @param int $value
     *
     * @return mixed
     */
    public function setYCoordinate(int $value);

    /**
     * MUST set piece color according to the allowed once in the enum
     *
     * @param PieceColorEnum $pieceColorEnum
     *
     * @return mixed
     */
    public function setPieceColor(PieceColorEnum $pieceColorEnum);

    /**
     * MUST handle all actions related with the movement of a piece. CAPTURE MUST BE considered
     * a move and proceed with caution since the PAWN has deviations from the conventional movement.
     * ( Diagonal capture and En Passant )
     *
     * @param MovementTypeEnum $movementTypeEnum
     * @param int $newX
     * @param int $newY
     *
     * @return mixed
     */
    public function move(MovementTypeEnum $movementTypeEnum, int $newX, int $newY);

    /**
     * @return string
     */
    public function toString(): string ;
}
