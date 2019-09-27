<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\Exception\IllegalPosition;
use SolarWinds\Chess\Util\MovementTypeEnum;
use SolarWinds\Chess\Pieces\Pawn;
use SolarWinds\Chess\Util\PieceColorEnum;


class PawnTest extends \PHPUnit_Framework_TestCase
{

    /** @var  ChessBoard */
    private $_chessBoard;
    /** @var  Pawn */
    private $_testSubject;

    protected function setUp()
    {
        $this->_chessBoard = new ChessBoard();
        $this->_testSubject = new Pawn(PieceColorEnum::WHITE());
        $this->_testSubject->setChessBoard($this->_chessBoard);

    }

    public function testChessBoard_Add_Sets_XCoordinate()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
    }

    public function testChessBoard_Add_Sets_YCoordinate()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_IllegalCoordinates_Right_DoesNotMove()
    {
        $testSubject = new Pawn(PieceColorEnum::BLACK());
        $testSubject->setChessBoard($this->_chessBoard);

        $this->_chessBoard->add($testSubject, 6, 3);
        $testSubject->move(MovementTypeEnum::MOVE(), 7, 3);


        $this->assertEquals(6, $testSubject->getXCoordinate());
        $this->assertEquals(3, $testSubject->getYCoordinate());
    }

    public function testPawn_Move_IllegalCoordinates_Left_DoesNotMove()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 4, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 7, 3);

        $this->assertEquals(7, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testIllegalPositionException()
    {
        $testSubject = new Pawn(PieceColorEnum::BLACK());
        $testSubject->setChessBoard($this->_chessBoard);

        $this->expectException(IllegalPosition::class);
        $this->_chessBoard->add($testSubject, 1, 10);
    }

}
