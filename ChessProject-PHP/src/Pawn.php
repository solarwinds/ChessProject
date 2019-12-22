<?php

namespace SolarWinds\Chess;

class Pawn extends Piece
{
    public function validMove (int $newX, int $newY) {
        if ( !$this->validMoveBase($newX,$newY) )
            return FALSE;
        
        if ( !$this->validPattern($newX, $newY) )
            return FALSE;
        
        // TODO: check the destination cell
        
        return TRUE;
    }
    public function validPattern (int $newX, int $newY) {
        // TODO: direction
        // TODO: captures
        // TODO: double‑move
        // TODO: promotion
        
        if ( $newX != $this->xCoordinate )
            return FALSE;
        
        if ( $newY == $this->yCoordinate+1 )
            return TRUE;
        
        if ( $newY == $this->yCoordinate-1 )
            return TRUE;
        
        return FALSE;
    }
}
