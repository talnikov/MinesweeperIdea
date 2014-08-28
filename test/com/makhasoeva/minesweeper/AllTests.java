package com.makhasoeva.minesweeper;

import com.makhasoeva.minesweeper.View.MinesweeperViewTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.makhasoeva.minesweeper.Model.BoardTest;
import com.makhasoeva.minesweeper.Model.SquareTest;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, SquareTest.class, MinesweeperViewTest.class })
public class AllTests {

}
