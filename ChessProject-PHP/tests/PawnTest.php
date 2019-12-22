<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\ChessBoard;
use SolarWinds\Chess\Pawn;
use SolarWinds\Chess\PieceColorEnum;

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
        $this->_testWhite = new Pawn(PieceColorEnum::WHITE());
        $this->_testBlack = new Pawn(PieceColorEnum::BLACK());
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
    }
    // TODO: test black captures white
    // TODO: test failed captures
}
