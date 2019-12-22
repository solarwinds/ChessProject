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
    private $_testSubject;
    
    /** @var  Pawn */
    private $_testBlack;
    
    protected function setUp () {
        $this->_chessBoard = new ChessBoard();
        $this->_testSubject = new Pawn(PieceColorEnum::WHITE());
        $this->_testBlack = new Pawn(PieceColorEnum::BLACK());
    }
    
    public function testChessBoard_Add_Sets_XCoordinate () {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
    }
    public function testChessBoard_Add_Sets_YCoordinate () {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }
    
    public function testPawn_Move_IllegalDestination_High () {
        $height = $this->_chessBoard->getSquareHeight();
        $this->_chessBoard->add($this->_testSubject, 0, $height-1);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testSubject->move(0, $height);
    }
    public function testPawn_Move_IllegalDestination_Low () {
        $this->_chessBoard->add($this->_testBlack, 0, 0);
        
        $this->expectException(\InvalidArgumentException::class);
        $this->_testBlack->move(0, -1);
    }
    public function testPawn_Move_IllegalCoordinates_Right_DoesNotMove () {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testSubject->move(7, 3);
    }
    public function testPawn_Move_IllegalCoordinates_Left_DoesNotMove () {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testSubject->move(5, 3);
    }
    
    public function testPawn_Move_White_Upwards () {
        $this->_chessBoard->add($this->_testSubject, 6, 2);
        $this->_testSubject->move(6, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }
    public function testPawn_Move_White_Downwards () {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->expectException(\InvalidArgumentException::class);
        $this->_testSubject->move(6, 2);
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
    
    // TODO: test captures
}
