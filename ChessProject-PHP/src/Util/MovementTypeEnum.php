<?php

namespace SolarWinds\Chess\Util;

class MovementTypeEnum
{
    const MOVE = 0;
    const CAPTURE = 1;

    private static $_instance = false;
    private static $_move;
    private static $_capture;

    private $_id;

    /**
     * MovementTypeEnum constructor.
     * @param int $_id
     */
    private function __construct(int $_id)
    {
        $this->_id = $_id;
    }

    /**
     * @return MovementTypeEnum
     */
    public static function MOVE(): self
    {
        self::initialise();

        return self::$_move;
    }

    /**
     * @return MovementTypeEnum
     */
    public static function CAPTURE(): self
    {
        self::initialise();

        return self::$_capture;
    }

    private static function initialise()
    {
        if (self::$_instance) {
            return;
        }

        self::$_move = new MovementTypeEnum(self::MOVE);
        self::$_capture = new MovementTypeEnum(self::CAPTURE);
        self::$_instance = true;
    }

}
