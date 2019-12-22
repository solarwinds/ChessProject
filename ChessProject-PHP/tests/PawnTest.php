<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\ChessBoard;
use SolarWinds\Chess\Pawn;

// TODO: we should swap the \InvalidArgumentException with a custom InvalidMoveException

class PawnTest extends \PHPUnit_Framework_TestCase
{
    /** @var  ChessBoard */
    private $_chessBoard;
    
    /** @var  Pawn */
    private $_testWhite;
    
    /** @var  Pawn */
    private $_testBlack;
    
    protected function setUp () {
        $this->_chessBoard = new ChessBoard();
        $this->_testWhite = new Pawn(TRUE);
        $this->_testBlack = new Pawn(FALSE);
    }
    
    public function testChessBoard_Add_Sets_XCoordinate () {
        $this->_chessBoard->add($this->_testWhite, 6, 3);
        $this->assertEquals(6, $this->_testWhite->getXCoordinate());
    }
    public function testChessBoard_Add_Sets_YCoordinate () {
        $this->_chessBoard->add($this->_testWhite, 6, 3);
        $this->assertEquals(3, $this->_testWhite->getYCoordinate());
    }
    
    public function testPawn_Move_IllegalDestination_High () {
        $height = $this->_chessBoard->getSquareHeight();
        $this->_chessBoard->add($this->_testWhite, 0, $height-1);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(0, $height);
    }
    public function testPawn_Move_IllegalDestination_Low () {
        $this->_chessBoard->add($this->_testBlack, 0, 0);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testBlack->move(0, -1);
    }
    public function testPawn_Move_IllegalCoordinates_Right_DoesNotMove () {
        $this->_chessBoard->add($this->_testWhite, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(7, 3);
    }
    public function testPawn_Move_IllegalCoordinates_Left_DoesNotMove () {
        $this->_chessBoard->add($this->_testWhite, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(5, 3);
    }
    
    /**
     * Trying to move a Pawn forward into a cell containing another Pawn of the same colour.
     */
    public function testPawn_Move_Illegal_Obstacle () {
        $secondWhite = new Pawn(TRUE);
        
        $this->_chessBoard->add($this->_testWhite, 5, 5);
        $this->_chessBoard->add($secondWhite, 5, 6);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(5, 6);
    }
    
    public function testPawn_Move_White_Upwards () {
        $this->_chessBoard->add($this->_testWhite, 6, 2);
        $this->_testWhite->move(6, 3);
        $this->assertEquals(6, $this->_testWhite->getXCoordinate());
        $this->assertEquals(3, $this->_testWhite->getYCoordinate());
    }
    public function testPawn_Move_White_Downwards () {
        $this->_chessBoard->add($this->_testWhite, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(6, 2);
    }
    public function testPawn_Move_Black_Downwards () {
        $this->_chessBoard->add($this->_testBlack, 4, 4);
        $this->_testBlack->move(4, 3);
        $this->assertEquals(4, $this->_testBlack->getXCoordinate());
        $this->assertEquals(3, $this->_testBlack->getYCoordinate());
    }
    public function testPawn_Move_Black_Upwards () {
        $this->_chessBoard->add($this->_testBlack, 4, 4);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testBlack->move(4, 5);
    }
    
    public function testPawn_White_Capture () {
        $this->_chessBoard->add($this->_testWhite, 4, 1);
        $this->_chessBoard->add($this->_testBlack, 5, 2);
        
        // Checks the capture move itself
        $this->_testWhite->move(5, 2);
        $this->assertEquals(5, $this->_testWhite->getXCoordinate());
        $this->assertEquals(2, $this->_testWhite->getYCoordinate());
        
        // Checks that in the target cell there is the active piece
        $pieceGot = $this->_chessBoard->getCell(5, 2);
        $this->assertEquals($this->_testWhite, $pieceGot);
        
        // Checks that the victim has actually been captured
        $this->assertTrue( $this->_testBlack->isCaptured() );
        $this->assertEquals(Piece::INVALID, $this->_testBlack->getXCoordinate());
        $this->assertEquals(Piece::INVALID, $this->_testBlack->getYCoordinate());
    }
    public function testPawn_Black_Capture () {
        $this->_chessBoard->add($this->_testWhite, 6, 5);
        $this->_chessBoard->add($this->_testBlack, 7, 6);
        
        // Checks the capture move itself
        $this->_testBlack->move(6, 5);
        $this->assertEquals(6, $this->_testBlack->getXCoordinate());
        $this->assertEquals(5, $this->_testBlack->getYCoordinate());
        
        // Checks that in the target cell there is the active piece
        $pieceGot = $this->_chessBoard->getCell(6, 5);
        $this->assertEquals($this->_testBlack, $pieceGot);
        
        // Checks that the victim has actually been captured
        $this->assertTrue( $this->_testWhite->isCaptured() );
        $this->assertEquals(Piece::INVALID, $this->_testWhite->getXCoordinate());
        $this->assertEquals(Piece::INVALID, $this->_testWhite->getYCoordinate());
    }
    
    /**
     * Trying to move a Pawn straight into a Pawn of a different colour
     */
    public function testPawn_White_FailedCapture_Forward () {
        $this->_chessBoard->add($this->_testWhite, 4, 1);
        $this->_chessBoard->add($this->_testBlack, 4, 2);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testWhite->move(4, 2);
    }
    
    public function testPawn_Black_FailedCapture_Forward () {
        throw new \Exception("Not implemented");
    }
    public function testPawn_FailedCapture_SameColour () {
        // TODO: trying to capture a Piece of the same Colour (doing the correct diagonal move)
        throw new \Exception("Not implemented");
    }
}
