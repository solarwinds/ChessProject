<?php

namespace SolarWinds\Chess\Exceptions;

class imposibleCoordinates extends \RuntimeException
{
    protected $message = 'Imposible coordinate %s';

    public function __construct($xCoordinate, $yCoordinate)
    {
        $this->message = sprintf($this->message, "$xCoordinate:$yCoordinate");

        parent::__construct();
    }
} 