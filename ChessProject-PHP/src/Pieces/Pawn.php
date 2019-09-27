<?php

namespace SolarWinds\Chess\Pieces;


use SolarWinds\Chess\ChessBoard;
use SolarWinds\Chess\Exception\IllegalPosition;
use SolarWinds\Chess\Util\MovementTypeEnum;
use SolarWinds\Chess\Util\PieceColorEnum;

class Pawn implements PieceInterface
{
    const START_POSITION_WHITE = 1;
    const START_POSITION_BLACK = 6;
    const LEGAL_DISTANCE_MOVES = [
        PieceColorEnum::WHITE => [1, 2],
        PieceColorEnum::BLACK => [-1, -2]
    ];

    /** @var PieceColorEnum */
    private $pieceColorEnum;

    /** @var ChessBoard */
    private $chessBoard;

    /** @var int */
    private $xCoordinate;

    /** @var int */
    private $yCoordinate;

    /**
     * Pawn constructor.
     * @param PieceColorEnum $pieceColorEnum
     */
    public function __construct(PieceColorEnum $pieceColorEnum)
    {
        $this->pieceColorEnum = $pieceColorEnum;
    }

    /**
     * @return ChessBoard
     */
    public function getChessBoard(): ChessBoard
    {
        return $this->chessBoard;
    }

    /**
     * @param ChessBoard $chessBoard
     * @return Pawn
     */
    public function setChessBoard(ChessBoard $chessBoard): PieceInterface
    {
        $this->chessBoard = $chessBoard;
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
     * @param $value
     * @return Pawn
     */
    public function setXCoordinate(int $value): PieceInterface
    {
        $this->xCoordinate = $value;
        return $this;
    }

    /**
     * @return int
     */
    public function getYCoordinate(): int
    {
        return $this->yCoordinate;
    }

    /**
     * @param int $value
     * @return Pawn
     */
    public function setYCoordinate(int $value): PieceInterface
    {
        $this->yCoordinate = $value;
        return $this;
    }

    /**
     * @return PieceColorEnum
     */
    public function getPieceColor(): PieceColorEnum
    {
        return $this->pieceColorEnum;
    }

    /**
     * @param MovementTypeEnum $movementTypeEnum
     * @param $newX
     * @param $newY
     */
    public function move(MovementTypeEnum $movementTypeEnum, int $newX, int $newY): void
    {
        if (!$this->getChessBoard()->isLegalBoardPosition($newX, $newY)) {
            throw new IllegalPosition($newX, $newY);
        }

        switch (true) {
            case (MovementTypeEnum::MOVE() == $movementTypeEnum):
                $this->makeMove($newX, $newY);
                break;
            case (MovementTypeEnum::CAPTURE() == $movementTypeEnum):
                throw new \RuntimeException(sprintf('TODO implement method %s', __METHOD__));
                break;
        }
    }

    /**
     * @return string
     */
    public function toString()
    {
        return "x({$this->xCoordinate}), y({$this->yCoordinate}), pieceColor({$this->pieceColorEnum})";
    }

    private function makeMove(int $newX, int $newY): void
    {
        $allowedMoves = $this->getLegalDistanceMoves();
        if (($newY === $this->getYCoordinate()) //Y can change if "capture"
            &&
            (
                ( //Starting position
                    in_array($this->getXCoordinate(), $this->getStartPositions()) &&
                    in_array(($newX - $this->getXCoordinate()), $allowedMoves)
                )
                || //Any next movement
                (in_array($newX - $this->getXCoordinate(), $allowedMoves))
            )
        ) {
            $this->getChessBoard()->add($this, $newX, $newY);
        }
    }

    private function getLegalDistanceMoves(): array
    {
        return self::LEGAL_DISTANCE_MOVES[(int)(string)$this->getPieceColor()];
    }

    private function getStartPositions(): array
    {
        return [self::START_POSITION_WHITE, self::START_POSITION_BLACK];
    }
}