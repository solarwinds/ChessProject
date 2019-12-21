#!/bin/bash

if [ -f result_tests.txt ]; then
	cp result_tests.txt result_tests_prev.txt
	truncate -s0 result_tests.txt
fi
./vendor/bin/phpunit --bootstrap vendor/autoload.php tests/ >result_tests.txt
mate result_tests.txt
