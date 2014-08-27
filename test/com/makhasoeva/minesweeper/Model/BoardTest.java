package com.makhasoeva.minesweeper.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.makhasoeva.minesweeper.Model.Square.States;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
		board = null;
	}

	@Test
	public void testSquaresGridSize() throws Exception {
		Square[][] grid = board.getGrid();
		assertEquals(Board.COLS, grid.length);
		for (Square squareRow[] : grid) {
			assertEquals(Board.ROWS, squareRow.length);
		}
	}

	@Test
	public void testSquaresNotNull() throws Exception {
		Square[][] grid = board.getGrid();
		for (Square[] squareRow : grid) {
			for (Square square : squareRow) {
				assertNotNull(square);
			}
		}
	}

	@Test
	public void testMinesQuantity() throws Exception {
		Square[][] grid = board.getGrid();
		int mines = 0;
		for (Square[] squareRow : grid) {
			for (Square square : squareRow) {
				if (square.isMine())
					mines++;
			}
		}
		assertEquals(mines, Board.MINES);
	}

	@Test
	public void testUncoverUnmarkedMines() throws Exception {
		Square[][] grid = board.getGrid();
		for (int i = 0; i < Board.COLS; i++) {
			for (int j = 0; j < Board.ROWS; j++) {
				Square square = grid[i][j];
				if (square.isMine() && square.getState() == States.COVERED) {
					try {
						board.uncoverSquare(i, j);
						fail("Mine's detonator failed");
					} catch (ExplosionException e) {
					}
				}
			}
		}
	}

	@Test
	public void testUncoverNonExistentSquare() throws Exception {
		Random nRandom = new Random();
		int x = nRandom.nextInt(Board.COLS) + Board.COLS;
		int y = nRandom.nextInt(Board.ROWS) + Board.ROWS;

		try {
			board.uncoverSquare(x, y);
			fail("No such square!");
		} catch (NoSuchSquareException e) {
		}

		try {
			board.uncoverSquare(x % Board.COLS, y);
			fail("No such square!");
		} catch (NoSuchSquareException e) {
		}

		try {
			board.uncoverSquare(x, y % Board.ROWS);
			fail("No such square!");
		} catch (NoSuchSquareException e) {
		}

	}
}
