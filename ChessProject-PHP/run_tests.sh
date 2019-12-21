#!/bin/bash

if [ -f result_tests.txt ]; then
	mv result_tests.txt result_tests_prev.txt
fi
./vendor/bin/phpunit --bootstrap vendor/autoload.php tests/ >result_tests.txt
mate result_tests.txt
