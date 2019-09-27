<?php


namespace SolarWinds\Chess\Exception;


class IllegalPosition extends \RuntimeException
{
    /** @var string  */
    protected $message = 'Illegal position %s';

    /**
     * IllegalPosition constructor.
     * @param $newX
     * @param $newY
     */
    public function __construct(int $newX, int $newY)
    {
        $this->message = sprintf($this->message, "$newX, $newY");

        parent::__construct();
    }

}
