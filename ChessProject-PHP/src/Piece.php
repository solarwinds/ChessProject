<?php

namespace SolarWinds\Chess;

/**
 * Every Piece type will have to derive from this class.
 * They will only have to implement validPattern() and validPath(), while everything else is handled here.
 */
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
    
    private $_isCaptured;
    
    public function __construct (bool $isWhite) {
        $this->xCoordinate = static::INVALID;
        $this->yCoordinate = static::INVALID;
        $this->_isWhite = $isWhite;
    }
    
    /**
     * Initialises a Piece on a Board, in a given position.
     * The Piece's state is reset.
     */
    public function initialise (ChessBoard $chessBoard, int $xCoordinate, int $yCoordinate) {
        $this->chessBoard = $chessBoard;
        $this->xCoordinate = $xCoordinate;
        $this->yCoordinate = $yCoordinate;
        $this->_isCaptured = FALSE;
    }
    
    /** @return int */
    public function getX () {
        return $this->xCoordinate;
    }
    
    /** @return int */
    public function getY () {
        return $this->yCoordinate;
    }
    
    public function isWhite () {
        return $this->_isWhite;
    }
    public function isBlack () {
        return !$this->_isWhite;
    }
    
    /**
     * Use this, with the identity operator ===, to check if two Pieces are of the same colour.
     */
    public function colourName () {
        if ( $this->isWhite() )
            return 'white';
        if ( $this->isBlack() )
            return 'black';
        throw new \InvalidArgumentException("Unknown colour for [$this].");
    }
    
    /**
     * Checks if a Piece is active, i.e. hasn't been captured nor promoted.
     * 
     * @return bool
     */
    public function isActive () {
        if ( $this->_isCaptured )
            return FALSE;
        
        // TODO: promotion
        
        return TRUE;
    }
    
    /**
     * Returns TRUE if $check is of the same colour of $this.
     * 
     * @return bool
     */
    public function isFriendly (Piece $check) {
        return $check->colourName()===$this->colourName();
    }
    
    /**
     * Moves a piece to a specific position.
     * 
     * @throws InvalidMoveException if the move isn't valid.
     */
    public function move (int $newX, int $newY) {
        $former_x = $this->xCoordinate;
        $former_y = $this->yCoordinate;
        
        /* NOTE: validMove() raises an InvalidMoveException in case the move isn't valid.
        
        If you end up here, it's because you already assessed that the move is valid, calling directly validMove().
        In the user interface, you'll have to check validMove for every possible cell where you'd like to move,
        and only allow move() to be called for a valid one.
        
        Still, this check is here for safety. It's not expensive at all, so it's better not to remove it.
        In case maximum performance becomes an issue, we can add a new bool parameter defaulting to FALSE,
        that skips the check if TRUE.
        */
        $dest_cell = $this->validMove($newX,$newY);
        
        if ($dest_cell instanceof Piece) {
            // Capture
            $dest_cell->CapturedBy($this);
        }
        
        $this->xCoordinate = $newX;
        $this->yCoordinate = $newY;
        
        $this->chessBoard->handleMove($this,$former_x,$former_y);
        
        /* TODO: promotion:
        [ ] Spawn a new Piece at the same coordinates (mark it as "promotion spawned", as opposite to "spawned at start")
        [ ] Disable this one (have a new bool for this, and update isActive())
        [ ] Update the Board
        
        The tricky part is deciding which is the new Piece to be spawned, but how to implement that cannot be decided
        at this stage, since we don't even know how the final interface is going to be.
        */
    }
    
    /**
     * Checks if a move is valid.
     * 
     * @throws InvalidMoveException if the move isn't valid.
     * @return Piece|TRUE Returns TRUE if the move is valid and the destination cell is empty, the captured Piece otherwise.
     */
    public function validMove (int $newX, int $newY) {
        if ( !$this->isActive() )
            throw new InvalidMoveException("$this: Inactive pieces can't move.");
        
        if ( !$this->chessBoard->isLegalBoardPosition($newX,$newY) )
            throw new InvalidMoveException("$this: Illegal board position [$newX, $newY].");
        
        if ( !$this->validPattern($newX, $newY) )
            throw new InvalidMoveException("$this: Illegal moving pattern [$newX, $newY].");
        
        if ( !$this->validPath($newX, $newY) )
            throw new InvalidMoveException("$this: Illegal moving path [$newX, $newY].");
        
        $dest_cell = $this->chessBoard->getCell($newX,$newY);
        if ( $dest_cell === ChessBoard::EMPTY ) {
            // The destination cell is empty
            
            return TRUE;
        }
        else {
            // The Piece in the destination cell must be of a different colour
            
            if ( $dest_cell->isFriendly($this) )
                throw new InvalidMoveException("$this: Can't capture your own piece [$newX, $newY].");
            
            return $dest_cell; // Returning the to‑be‑captured Piece
        }
        
        throw new \LogicException("Should never end up here.");
    }
    
    /**
     * This only checks if the moving pattern is correct.
     * That is: it doesn't check if the cell exists, is empty, there's no pieces blocking the path, etc.
     * It only and solely checks the pattern (i.e. the direction, the distance, etc.)
     * 
     * @return bool TRUE if valid, FALSE otherwise.
     */
    abstract protected function validPattern (int $newX, int $newY);
    
    /**
     * This checks if the path that the Piece has to walk is clear.
     * Pieces that move by a single square (King), or that ignore the path (Knight) will just return TRUE.
     * This DOES NOT check the destination cell, only the path towards it!
     * 
     * @return bool TRUE if valid, FALSE otherwise.
     */
    abstract protected function validPath (int $newX, int $newY);
    
    /**
     * Call this method when $this has been captured.
     * 
     * @param Piece The Piece that captured $this.
     */
    public function capturedBy (Piece $by) {
        // NOTE: while $by isn't currently used, we might easily need it for displaying, or for logging.
        
        $this->_isCaptured = TRUE;
        $this->xCoordinate = static::INVALID;
        $this->yCoordinate = static::INVALID;
    }
    
    public function __toString () {
        return get_class($this)." ".$this->colourName()." @({$this->xCoordinate}, {$this->yCoordinate})";
    }
}
