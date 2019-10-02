<?php

namespace SolarWinds\Chess;

use PHPUnit_Framework_TestCase;
use SolarWinds\Chess\Domain\ChessBoard\ChessBoard;
use SolarWinds\Chess\Domain\Piece\MovementTypeEnum;
use SolarWinds\Chess\Domain\Piece\Pawn;
use SolarWinds\Chess\Domain\Piece\PieceColorEnum;

/**
 * Class PawnTest
 *
 * @package SolarWinds\Chess
 */
class PawnTest extends PHPUnit_Framework_TestCase
{
    /**
     * @var ChessBoard
     */
    private $_chessBoard;

    /**
     * @var Pawn
     */
    private $_testSubject;

    protected function setUp()
    {
        $this->_chessBoard = new ChessBoard();
        $this->_testSubject = new Pawn(PieceColorEnum::WHITE());
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
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 7, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_IllegalCoordinates_Left_DoesNotMove()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 4, 3);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_ForWhite()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 6, 4);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(4, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_IllegalCoordinates_Backwards_DoesNotMove_ForWhite()
    {
        $this->_chessBoard->add($this->_testSubject, 6, 3);
        $this->_testSubject->move(MovementTypeEnum::MOVE(), 6, 2);
        $this->assertEquals(6, $this->_testSubject->getXCoordinate());
        $this->assertEquals(3, $this->_testSubject->getYCoordinate());
    }

    public function testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates_ForBlack()
    {
        $blackPawn = $this->_testSubject = new Pawn(PieceColorEnum::BLACK());
        $this->_chessBoard->add($blackPawn, 6, 3);
        $blackPawn->move(MovementTypeEnum::MOVE(), 6, 2);
        $this->assertEquals(6, $blackPawn->getXCoordinate());
        $this->assertEquals(2, $blackPawn->getYCoordinate());
    }

    public function testPawn_Move_IllegalCoordinates_Backwards_DoesNotMove_FoBlack()
    {
        $blackPawn = $this->_testSubject = new Pawn(PieceColorEnum::BLACK());
        $this->_chessBoard->add($blackPawn, 6, 3);
        $blackPawn->move(MovementTypeEnum::MOVE(), 6, 4);
        $this->assertEquals(6, $blackPawn->getXCoordinate());
        $this->assertEquals(3, $blackPawn->getYCoordinate());
    }

    public function testPawn_Duplicate_AfterMove()
    {
        $blackPawn = $this->_testSubject = new Pawn(PieceColorEnum::BLACK());
        $this->_chessBoard->add($blackPawn, 6, 3);
        $blackPawn->move(MovementTypeEnum::MOVE(), 6, 2);
        $currentChessBoard = $blackPawn->getChessBoard();
        $this->assertEquals(6, $blackPawn->getXCoordinate());
        $this->assertEquals(2, $blackPawn->getYCoordinate());
        $this->assertEquals(ChessBoard::EMPTY_FIELD, $currentChessBoard->getPieceByCoordinates(6,3));
    }

}
