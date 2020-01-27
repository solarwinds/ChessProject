<?php

namespace SolarWinds\Chess;

use SolarWinds\Chess\Exceptions\imposibleCoordinates;
use SolarWinds\Chess\Pieces\Pawn;
use SolarWinds\Chess\Util\PieceColorEnum;

class ChessBoardTest extends \PHPUnit_Framework_TestCase
{

    /** @var  ChessBoard */
    private $_testSubject;

    public function setUp()
    {
        $this->_testSubject = new ChessBoard();
    }

    public function testHas_MaxBoardWidth_of_8()
    {
        $this->assertEquals(8, ChessBoard::MAX_BOARD_WIDTH);
    }

    public function testHas_MaxBoardHeight_of_8()
    {
        $this->assertEquals(8, ChessBoard::MAX_BOARD_HEIGHT);
    }

    public function testIsLegalBoardPosition_True_X_equals_1_Y_equals_1()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(1, 1);
        $this->assertTrue($isValidPosition);
    }

    public function testIsLegalBoardPosition_True_X_equals_5_Y_equals_5()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(5, 5);
        $this->assertTrue($isValidPosition);
    }

    public function testIsLegalBoardPosition_False_X_equals_11_Y_equals_5()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(11, 5);
        $this->assertFalse($isValidPosition);
    }

    public function testIsLegalBoardPosition_False_X_equals_0_Y_equals_9()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(0, 9);
        $this->assertFalse($isValidPosition);
    }

    public function testIsLegalBoardPosition_False_X_equals_11_Y_equals_0()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(11, 0);
        $this->assertFalse($isValidPosition);
    }

    public function testIsLegalBoardPosition_False_For_Negative_Y_Values()
    {
        $isValidPosition = $this->_testSubject->isLegalBoardPosition(5, -1);
        $this->assertFalse($isValidPosition);
    }

    public function testAvoids_Duplicate_Positioning()
    {
        $firstPawn = new Pawn(PieceColorEnum::BLACK());
        $secondPawn = new Pawn(PieceColorEnum::BLACK());
        $this->_testSubject->add($firstPawn, 6, 3);
        $this->_testSubject->add($secondPawn, 6, 3);
        $this->assertEquals(6, $firstPawn->getXCoordinate());
        $this->assertEquals(3, $firstPawn->getYCoordinate());
        $this->assertEquals(-1, $secondPawn->getXCoordinate());
        $this->assertEquals(-1, $secondPawn->getYCoordinate());
    }

    public function testLimits_The_Number_Of_Pawns()
    {
        for ($i = 0; $i < 10; $i++) {
            $pawn = new Pawn(PieceColorEnum::BLACK());
            $row = round($i / ChessBoard::MAX_BOARD_WIDTH, 0, PHP_ROUND_HALF_UP);

            $xCoordinate = round(6 + $row, 0, PHP_ROUND_HALF_UP);
            $yCoordinate = round($i % ChessBoard::MAX_BOARD_WIDTH, 0, PHP_ROUND_HALF_UP);

            $this->_testSubject->add($pawn, $xCoordinate, $yCoordinate);

            if ($row <= 1) {
                $this->assertEquals($xCoordinate, $pawn->getXCoordinate());
                $this->assertEquals($yCoordinate, $pawn->getYCoordinate());
            } else {
                $this->assertEquals(-1, $pawn->getXCoordinate());
                $this->assertEquals(-1, $pawn->getYCoordinate());
            }
        }
    }
    
    public function testImposibleCoordinatesException()
    {
        $pawn = new Pawn(PieceColorEnum::BLACK());

        $this->expectException(imposibleCoordinates::class);
        $this->_testSubject->add($pawn, 1, 10);
    }

}
