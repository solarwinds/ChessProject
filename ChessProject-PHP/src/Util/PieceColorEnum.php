<?php

namespace SolarWinds\Chess\Util;

class PieceColorEnum
{
    const WHITE = 0;
    const BLACK = 1;

    private static $_instance = false;
    private static $_white;
    private static $_black;

    private $_id;

    /**
     * PieceColorEnum constructor.
     * @param int $_id
     */
    private function __construct(int $_id)
    {
        $this->_id = $_id;
    }

    /**
     * @return PieceColorEnum
     */
    public static function WHITE(): self
    {
        self::initialise();

        return self::$_white;
    }

    /**
     * @return PieceColorEnum
     */
    public static function BLACK(): self
    {
        self::initialise();

        return self::$_black;
    }

    private static function initialise(): void
    {
        if (self::$_instance) {
            return;
        }

        self::$_white = new PieceColorEnum(self::WHITE);
        self::$_black = new PieceColorEnum(self::BLACK);
        self::$_instance = true;
    }

    public function __toString()
    {
        return (string)$this->_id;
    }

}
