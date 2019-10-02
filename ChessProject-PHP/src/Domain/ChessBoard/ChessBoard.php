<?php

namespace SolarWinds\Chess\Domain\ChessBoard;

use SolarWinds\Chess\Domain\Piece\Contracts\PieceInterface;

/**
 * Class ChessBoard
 *
 * @package SolarWinds\Chess
 */
class ChessBoard
{
    /**
     * Bellow 4 constants describe MAX and MIN width and height of the chess board
     * as well as all four corners of the board
     */
    const MAX_BOARD_WIDTH = 8;
    const MAX_BOARD_HEIGHT = 8;
    const MIN_BOARD_WIDTH = 0;
    const MIN_BOARD_HEIGHT = 0;

    /**
     * This is the default value of the board with no pieces on it
     */
    const EMPTY_FIELD = 0;

    /**
     * This is where all illegal set pieces should be store ( both X and Y )
     */
    const ILLEGAL_MOVE_COORDINATE = -1;

    /**
     * The actual board with all the current pieces on it
     *
     * @var array
     */
    private $pieces;

    /**
     * Creates an empty chess board.
     *
     * ChessBoard constructor.
     */
    public function __construct()
    {
        $this->pieces = array_fill(
            self::MIN_BOARD_WIDTH,
            self::MAX_BOARD_WIDTH,
            array_fill(
                self::MIN_BOARD_HEIGHT,
                self::MAX_BOARD_HEIGHT, self::EMPTY_FIELD
            )
        );
    }

    /**
     * This method MUST add a piece on the board if and only if the appropriate coordinates
     * are provided. If not then the piece MUST be set to coordinates corresponding to
     * ILLEGAL_MOVE_COORDINATE constant
     *
     * @param PieceInterface $piece
     * @param int $xCoordinate
     * @param int $yCoordinate
     *
     * @return $this
     */
    public function add(PieceInterface $piece, int $xCoordinate, int $yCoordinate): ChessBoard
    {
        if (!$this->isLegalBoardPosition($xCoordinate, $yCoordinate)
            || $this->pieces[$xCoordinate][$yCoordinate] !== self::EMPTY_FIELD
        ) {
            $piece->setXCoordinate(self::ILLEGAL_MOVE_COORDINATE);
            $piece->setYCoordinate(self::ILLEGAL_MOVE_COORDINATE);

            return $this;
        }

        $piece->setXCoordinate($xCoordinate);
        $piece->setYCoordinate($yCoordinate);
        $piece->setChessBoard($this);
        $this->pieces[$xCoordinate][$yCoordinate] = $piece;

        return $this;
    }

    /**
     * This method MUST check if the coordinates are inside the board
     *
     * @param $xCoordinate
     * @param $yCoordinate
     *
     * @return bool
     */
    public function isLegalBoardPosition(int $xCoordinate, int $yCoordinate): bool
    {
        return $xCoordinate >= self::MIN_BOARD_WIDTH
            && $xCoordinate < self::MAX_BOARD_WIDTH
            && $yCoordinate >= self::MIN_BOARD_HEIGHT
            && $yCoordinate < self::MAX_BOARD_HEIGHT;
    }

    /**
     * Removes a piece from the board.
     *
     * @param int $xCoordinate
     * @param int $yCoordinate
     *
     * @return bool
     */
    public function removePiece(int $xCoordinate, int $yCoordinate): bool
    {
        return $this->pieces[$xCoordinate][$yCoordinate] = self::EMPTY_FIELD;
    }

    /**
     * Returns the current piece for a specific location coordinates on the board
     *
     * @param int $xCoordinate
     * @param int $yCoordinate
     *
     * @return mixed
     */
    public function getPieceByCoordinates(int $xCoordinate, int $yCoordinate)
    {
        return $this->pieces[$xCoordinate][$yCoordinate];
    }
}
