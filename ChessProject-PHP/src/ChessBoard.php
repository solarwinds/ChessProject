<?php

namespace SolarWinds\Chess;

class ChessBoard
{
    const EMPTY = -1;
    const BOARD_WIDTH = 8;
    const BOARD_HEIGHT = 8;
    
    private $cells; // Keep in mind this is basically a cache of the Piece positions, since each Piece will have its own coodrinates stored.
    
    public function __construct () {
        // NOTE: BOARD_* should be static::, not self::, because they should be overridable by a child class
        $this->cells = array_fill(0, static::BOARD_WIDTH, array_fill(0, static::BOARD_HEIGHT, self::EMPTY));
    }
    
    public function add (Pawn $pawn, int $xCoordinate, int $yCoordinate) {
        if ( !$this->isLegalBoardPosition($xCoordinate, $yCoordinate) )
            throw new InvalidMoveException("Invalid board position [$xCoordinate, $yCoordinate].");
        
        if ( !$this->isCellEmpty($xCoordinate, $yCoordinate) )
            throw new InvalidMoveException("Cell not empty [$xCoordinate, $yCoordinate].");
        
        if ( !is_int($xCoordinate) || !is_int($yCoordinate) )
            throw new InvalidMoveException("Non‑integer coordinate ($xCoordinate, $yCoordinate)(".gettype($xCoordinate).", ".gettype($yCoordinate).").");
        
        $this->cells[$xCoordinate][$yCoordinate] = $pawn; // NOTE: remember to update this!
        $pawn->initialise($this,$xCoordinate,$yCoordinate);
    }
    
    /**
 	 * @return boolean
 	 **/
    public function isLegalBoardPosition (int $xCoordinate, int $yCoordinate, bool $THROW=FALSE) {
        // NOTE: doing this is better than checking if they are between 0 and BOARD_*, since it technically supports different board configurations, and since it actually checks if that cell really exists, which is the point
        
        $ret = isset($this->cells[$xCoordinate][$yCoordinate]);
        if ( $THROW && !$ret )
            throw new InvalidMoveException("Not a legal board position [$xCoordinate, $yCoordinate].");
        return $ret;
    }
    
    public function isCellEmpty (int $xCoordinate, int $yCoordinate) {
        $this->isLegalBoardPosition($xCoordinate, $yCoordinate, TRUE);
        return $this->cells[$xCoordinate][$yCoordinate] === self::EMPTY;
    }
    
    public function getCells () {
        return $this->cells; // NOTE: returns a copy
    }
    
    /**
     * Returns the board size, assuming the board is square shaped.
     */
    public function getSquareSize () {
        return [$this->getWidth(),$this->getSquareHeight()];
    }
    
    /**
     * Returns the board width, regardless of the board shape.
     */
    public function getWidth () {
        return count($this->cells);
    }
    
    /**
     * Returns the board height, assuming the board is square shaped.
     */
    public function getSquareHeight () {
        return count($this->cells[0]);
    }
    
    /**
     * @return Pawn|EMPTY
     */
    public function getCell (int $xCoordinate, int $yCoordinate) {
        $this->isLegalBoardPosition($xCoordinate, $yCoordinate, TRUE);
        return $this->cells[$xCoordinate][$yCoordinate];
    }
    
    public function handleMove (Piece $movedPiece, $capturedPiece, int $formerX, int $formerY) {
        $this->cells[$formerX][$formerY] = static::EMPTY;
        $this->cells[$movedPiece->getX()][$movedPiece->getY()] = $movedPiece;
        
        if ($capturedPiece instanceof Piece) {
            // Capture
            $capturedPiece->CapturedBy($movedPiece);
        }
    }
}
