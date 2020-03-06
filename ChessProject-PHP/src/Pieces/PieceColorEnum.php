<?php

namespace SolarWinds\Chess\Pieces;

class PieceColorEnum
{
    private static $_instance = false;
    private static $_white;
    private static $_black;

    private $_id;

    const WHITE = 1;
    const BLACK = 2;

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

        self::$_white = new PieceColorEnum(self::WHITE);
        self::$_black = new PieceColorEnum(self::BLACK);
    }

}
