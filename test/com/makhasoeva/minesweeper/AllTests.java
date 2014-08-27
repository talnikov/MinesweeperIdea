package com.makhasoeva.minesweeper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.makhasoeva.minesweeper.Model.BoardTest;
import com.makhasoeva.minesweeper.Model.SquareTest;
import com.makhasoeva.minesweeper.View.UITest;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, SquareTest.class, UITest.class })
public class AllTests {

}
