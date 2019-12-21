#!/bin/sh

./vendor/bin/phpunit --bootstrap vendor/autoload.php tests/ >result_tests.txt
mate result_tests.txt
