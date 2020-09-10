package com.solarwindsmsp.chess.suite;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.solarwindsmsp.chess.ChessBoardTest;
import com.solarwindsmsp.chess.piece.PawnTest;
import com.solarwindsmsp.chess.suite.category.JUnitTest;

/**
 * Test Suite for all JUnit tests.
 *
 * @author jr185235
 *
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(JUnitTest.class)
@Suite.SuiteClasses({ ChessBoardTest.class, PawnTest.class })
public class JUnitTestSuite {

}
