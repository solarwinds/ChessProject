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
    
    protected function validPattern (int $newX, int $newY) {
        // TODO: captures
        // TODO: double‑move
        
        if ( $newX != $this->xCoordinate )
            return FALSE;
        
        if ( $this->goingUp() && $newY==$this->yCoordinate+1 )
            return TRUE;
        
        if ( $this->goingDown() && $newY==$this->yCoordinate-1 )
            return TRUE;
        
        return FALSE;
    }
    protected function validPath (int $newX, int $newY) {
        // TODO: double‑move
        
        return TRUE;
    }
    protected function validCapture (int $newX, int $newY) {
        throw new \Exception("Not implemented");
    }
}
