<?php

namespace SolarWinds\Chess\Domain\Piece;

use SolarWinds\Chess\Domain\Piece\Contracts\PieceInterface;
use SolarWinds\Chess\Domain\ChessBoard\ChessBoard;

/**
 * Class Pawn
 *
 * @package SolarWinds\Chess
 */
class Pawn implements PieceInterface
{
    /**
     * Default starting position for white
     */
    const DEFAULT_POSITION_WHITE = 1;

    /**
     * Default starting position for black
     */
    const DEFAULT_POSITION_BLACK = 6;

    /**
     * Default white pawn step on move action
     */
    const PAWN_DEFAULT_STEP_WITHOUT_CAPTURE = 1;

    /**
     * Default pawn step on the first move
     */
    const PAWN_FIRST_STEP_WITHOUT_CAPTURE = [1, 2];

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
     *
     * @param PieceColorEnum $pieceColorEnum
     */
    public function __construct(PieceColorEnum $pieceColorEnum)
    {
        $this->pieceColorEnum = $pieceColorEnum;
    }

    /**
     * @inheritDoc
     */
    public function getChessBoard(): ChessBoard
    {
        return $this->chessBoard;
    }

    /**
     * @inheritDoc
     */
    public function setChessBoard(ChessBoard $chessBoard)
    {
        $this->chessBoard = $chessBoard;
    }

    /**
     * @inheritDoc
     */
    public function getXCoordinate(): int
    {
        return $this->xCoordinate;
    }

    /**
     * @inheritDoc
     */
    public function setXCoordinate(int $value)
    {
        $this->xCoordinate = $value;
    }

    /**
     * @inheritDoc
     */
    public function getYCoordinate(): int
    {
        return $this->yCoordinate;
    }

    /**
     * @inheritDoc
     */
    public function setYCoordinate(int $value)
    {
        $this->yCoordinate = $value;
    }

    /**
     * @inheritDoc
     */
    public function getPieceColor()
    {
        return $this->pieceColorEnum;
    }

    /**
     * @inheritDoc
     */
    public function setPieceColor(PieceColorEnum $value)
    {
        $this->pieceColorEnum = $value;
    }

    /**
     * @inheritDoc
     */
    public function move(MovementTypeEnum $movementTypeEnum, int $newX, int $newY)
    {
        if ($movementTypeEnum == MovementTypeEnum::MOVE()) {
            $this->moveWithoutCapture($newX, $newY);
        }
    }

    /**
     * This MUST be the default move action. Base behavior of a pawn without capturing.
     *
     * @param int $newX
     * @param int $newY
     *
     * @return $this
     */
    private function moveWithoutCapture(int $newX, int $newY): Pawn
    {
        if ($this->getXCoordinate() !== $newX) {
            return $this;
        }

        if (($this->getPieceColor() == PieceColorEnum::WHITE() && $this->isLegalWhiteMove($newY))
            || ($this->getPieceColor() == PieceColorEnum::BLACK() && $this->isLegalBlackMove($newY))
        ) {
            $this->chessBoard->removePiece($this->getXCoordinate(), $this->getYCoordinate());
            $this->setYCoordinate($newY);
            $this->chessBoard->add($this, $this->getXCoordinate(), $newY);
        }

        return $this;
    }

    /**
     * MUST return true if a move for a white pawn is valid based on all preconditions.
     * A valid move is every move that goes +1 in columns and +2 on first move.
     *
     * @param int $newY
     *
     * @return bool
     */
    private function isLegalWhiteMove(int $newY): bool
    {
        $allowedSquare = $this->getYCoordinate() + self::PAWN_DEFAULT_STEP_WITHOUT_CAPTURE;
        $isDefaultPosition = $this->getYCoordinate() === self::DEFAULT_POSITION_WHITE;
        $allowedSquaresOnDefaultPosition = in_array(
            $newY - $this->getYCoordinate(),
            self::PAWN_FIRST_STEP_WITHOUT_CAPTURE
        );

        return $newY === $allowedSquare || ($isDefaultPosition && $allowedSquaresOnDefaultPosition);
    }

    /**
     * MUST return if a move for a black pawn is valid based on all preconditions.
     * A valid move is every move that goes -1 in columns and -2 on first move.
     *
     * @param int $newY
     *
     * @return bool
     */
    private function isLegalBlackMove(int $newY): bool
    {
        $allowedSquare = $this->getYCoordinate() - self::PAWN_DEFAULT_STEP_WITHOUT_CAPTURE === $newY;
        $isDefaultPosition = $this->getYCoordinate() === self::DEFAULT_POSITION_BLACK;
        $allowedSquaresOnDefaultPosition = in_array(
            $this->getYCoordinate() - $newY,
            self::PAWN_FIRST_STEP_WITHOUT_CAPTURE
        );

        return $newY === $allowedSquare || ($isDefaultPosition && $allowedSquaresOnDefaultPosition);
    }

    /**
     * @return string
     */
    public function toString(): string
    {
        return "x({$this->xCoordinate}), y({$this->yCoordinate}), pieceColor({$this->pieceColorEnum})";
    }
}
