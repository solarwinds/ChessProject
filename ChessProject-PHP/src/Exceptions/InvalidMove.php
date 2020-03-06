<?php


namespace SolarWinds\Chess\Exceptions;


class InvalidMove extends \Exception
{
    public function __construct(int $positionX, int $positionY)
    {
        $this->message = sprintf('Coordinates are not valid x:%s, y:%s',  $positionX, $positionY);

        parent::__construct();
    }
}