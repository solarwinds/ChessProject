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
    
    /**
     * Adds a Piece to the Board, in a specific position.
     * Its status is reset.
     */
    public function add (Piece $piece, int $xCoordinate, int $yCoordinate) {
        if ( !$this->isLegalBoardPosition($xCoordinate, $yCoordinate) )
            throw new InvalidMoveException("Invalid board position [$xCoordinate, $yCoordinate].");
        
        if ( !$this->isCellEmpty($xCoordinate, $yCoordinate) )
            throw new InvalidMoveException("Cell not empty [$xCoordinate, $yCoordinate].");
        
        if ( !is_int($xCoordinate) || !is_int($yCoordinate) )
            throw new InvalidMoveException("Non‑integer coordinate ($xCoordinate, $yCoordinate)(".gettype($xCoordinate).", ".gettype($yCoordinate).").");
        
        $this->cells[$xCoordinate][$yCoordinate] = $piece; // NOTE: remember to update this!
        $piece->initialise($this,$xCoordinate,$yCoordinate);
    }
    
    /**
     * Checks if a given Board position is legal.
     * 
     * @param int $xCoordinate X coordinate
     * @param int $yCoordinate Y coordinate
     * @param bool $THROW If TRUE and the position isn't legal, throw an InvalidMoveException.
     * 
 	 * @return bool TRUE if it's legal, FALSE otherwise.
 	 **/
    public function isLegalBoardPosition (int $xCoordinate, int $yCoordinate, bool $THROW=FALSE) {
        // NOTE: doing this is better than checking if they are between 0 and BOARD_*, since it technically supports different board configurations, and since it actually checks if that cell really exists, which is the point
        
        $ret = isset($this->cells[$xCoordinate][$yCoordinate]);
        if ( $THROW && !$ret )
            throw new InvalidMoveException("Not a legal board position [$xCoordinate, $yCoordinate].");
        return $ret;
    }
    
    /**
     * Checks if a cell is empty. As in "not occupied by a Piece".
     * 
     * @return bool TRUE if the cell is empty, FALSE otherwise.
     */
    public function isCellEmpty (int $xCoordinate, int $yCoordinate) {
        $this->isLegalBoardPosition($xCoordinate, $yCoordinate, TRUE);
        return $this->cells[$xCoordinate][$yCoordinate] === self::EMPTY;
    }
    
    /**
     * Returns a copy of the $cells array.
     * Keep in mind it's copy, not a reference!
     */
    public function getCells () {
        return $this->cells; // NOTE: returns a copy
    }
    
    /**
     * Returns the board size, assuming the board is square shaped.
     * 
     * @return array [width, height]
     */
    public function getSquareSize () {
        return [$this->getWidth(),$this->getSquareHeight()];
    }
    
    /**
     * Returns the board width, regardless of the board shape.
     * 
     * @return int
     */
    public function getWidth () {
        return count($this->cells);
    }
    
    /**
     * Returns the board height, assuming the board is square shaped.
     * 
     * @return int
     */
    public function getSquareHeight () {
        return count($this->cells[0]);
    }
    
    /**
     * Returns the content of a cell. The Piece in case it's not empty, EMPTY otherwise.
     * 
     * @return Piece|EMPTY
     */
    public function getCell (int $xCoordinate, int $yCoordinate) {
        $this->isLegalBoardPosition($xCoordinate, $yCoordinate, TRUE);
        return $this->cells[$xCoordinate][$yCoordinate];
    }
    
    /**
     * To be called to register a Piece movement, after the Piece itself has already performed it.
     */
    public function handleMove (Piece $movedPiece, int $formerX, int $formerY) {
        $this->cells[$formerX][$formerY] = static::EMPTY;
        $this->cells[$movedPiece->getX()][$movedPiece->getY()] = $movedPiece;
    }
}
