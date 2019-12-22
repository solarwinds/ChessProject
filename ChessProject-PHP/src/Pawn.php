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
        // TODO: double‑move
        
        if ( $this->goingUp() && $newY!=$this->yCoordinate+1 )
            return FALSE;
    
        if ( $this->goingDown() && $newY!=$this->yCoordinate-1 )
            return FALSE;
        
        $dest_cell = $this->chessBoard->getCell($newX,$newY);
        
        if ( $dest_cell === ChessBoard::EMPTY ) {
            // Moving into an empty cell, checking for a normal moving pattern
            
            if ( $newX != $this->xCoordinate )
                return FALSE;
        }
        else {
            // Moving into an occupied cell, checking for a capture pattern
            // NOTE: we won't check here for the colour of the captured Piece, this will be done in Piece's validMove()
            
            if ( ($newX != $this->xCoordinate+1) && ($newX != $this->xCoordinate-1) )
                return FALSE;
        }
        
        return TRUE;
    }
    protected function validPath (int $newX, int $newY) {
        // TODO: double‑move
        
        return TRUE;
    }
}
