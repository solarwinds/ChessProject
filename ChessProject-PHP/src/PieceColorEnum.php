<?php

namespace SolarWinds\Chess;

/*  TODO: this class is undocumented, and its purpose is a bit unclear.
    It look like a case of over‑engineering and over‑thinking, for something that could have been much simpler and clearer.
    If we at least had some documentation, we could have been sure about its purpose, and refactored it to meet it better.
    Missing that, we should both have to guess what it wanted to do, and change the "how".
    
    Unfortunately, this means that in its current state, the entirety of this class is a bit useless, likely to be scrapped
    entirely. I'm afraid I don't really see anything salvagable about it.
*/
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
