<?php

namespace SolarWinds\Chess;

class Pawn extends Piece
{
    private function goingUp () {
        return $this->isWhite();
    }
    private function goingDown () {
        return $this->isBlack();
    }
    
    public function validMove (int $newX, int $newY) {
        if ( !$this->validMoveBase($newX,$newY) )
            return FALSE;
        
        if ( !$this->validPattern($newX, $newY) )
            return FALSE;
        
        // TODO: check the destination cell
        
        return TRUE;
    }
    public function validPattern (int $newX, int $newY) {
        // TODO: captures
        // TODO: double‑move
        // TODO: promotion
        
        if ( $newX != $this->xCoordinate )
            return FALSE;
        
        if ( $this->goingUp() && $newY==$this->yCoordinate+1 )
            return TRUE;
        
        if ( $this->goingDown() && $newY==$this->yCoordinate-1 )
            return TRUE;
        
        return FALSE;
    }
}
