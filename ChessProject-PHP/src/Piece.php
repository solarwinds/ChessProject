<?php

namespace SolarWinds\Chess;

abstract class Piece
{
    const INVALID = -1;
    
    /** @var ChessBoard */
    protected $chessBoard;
    
    /** @var int */
    protected $xCoordinate;
    
    /** @var int */
    protected $yCoordinate;
    
    private $_isWhite;
    
    public function __construct (bool $isWhite) {
        $this->xCoordinate = static::INVALID;
        $this->yCoordinate = static::INVALID;
        $this->_isWhite = $isWhite;
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
    
    public function isWhite () {
        return $this->_isWhite;
    }
    public function isBlack () {
        return !$this->_isWhite;
    }
    public function colourName () {
        if ( $this->isWhite() )
            return 'white';
        if ( $this->isBlack() )
            return 'black';
        throw new \InvalidArgumentException("Unknown colour for [$this].");
    }
    
    /**
     * Returns TRUE if $check is of the same colour of $this.
     */
    public function isFriendly (Piece $check) {
        return $check->colourName()===$this->colourName();
    }
    
    /**
     * This function must call validMove().
     */
    public function move (int $newX, int $newY) {
        if ( !$this->validMove($newX,$newY) )
            throw new \InvalidArgumentException($this.": invalid move [$newX, $newY].");
        
        $this->xCoordinate = $newX;
        $this->yCoordinate = $newY;
        
        // TODO: captures
        // TODO: promotions
        // TODO: update the board
    }
    
    /**
     * This has to be called by validMove().
     * It features checks that are common for any Piece.
     */
    protected function validMoveBase (int $newX, int $newY) {
        if ( !$this->chessBoard->isLegalBoardPosition($newX,$newY) )
            return FALSE;
        
        $dest_cell = $this->chessBoard->getCell($newX,$newY);
        if ( $dest_cell !== ChessBoard::EMPTY ) {
            // Must not be of the same colour
            
            if ( $dest_cell->isFriendly($this) )
                return FALSE;
        }
        
        return TRUE;
    }
    
    /**
     * This must call validPattern(), and also check other related constraints, relative to the Board and its status.
     * For instance, it must check if the cell exists, and if it's not occupied by a piece of yours, and it there
     * are no pieces blocking its way, etc.
     */
    abstract public function validMove (int $newX, int $newY);
    
    /**
     * This only checks if the moving pattern is correct.
     * That is: it doesn't check if the cell exists, is empty, there's no pieces blocking the path, etc.
     * It only and solely checks the pattern (i.e. the direction, the distance, etc.)
     */
    abstract protected function validPattern (int $newX, int $newY);
    
    public function __toString () {
        return get_class($this)." ".($this->isWhite()?'white':'black')." @({$this->xCoordinate}, {$this->yCoordinate})";
    }
}
