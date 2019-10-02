<?php

namespace SolarWinds\Chess\Domain\Piece;

class PieceColorEnum
{
    private static $_instance = false;
    private static $_white;
    private static $_black;

    private $_id;

    private function __construct($_id)
    {
        $this->_id = $_id;
    }

    /** @return: PieceColorEnum */
    public static function WHITE()
    {
        self::initialise();

        return self::$_white;
    }

    /** @return: PieceColorEnum */
    public static function BLACK()
    {
        self::initialise();

        return self::$_black;
    }

    private static function initialise()
    {
        if (self::$_instance) {
            return;
        }

        self::$_white = new PieceColorEnum(1);
        self::$_black = new PieceColorEnum(2);
    }

}
