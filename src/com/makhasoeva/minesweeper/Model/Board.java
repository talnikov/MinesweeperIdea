package com.makhasoeva.minesweeper.Model;

import java.util.Random;

public class Board {
	public static final int MINES = 10;
	public static final int COLS = 9;
	public static final int ROWS = 9;

	private final Square[][] grid;

	public Square[][] getGrid() {
		return grid;
	}

	public Board() {
		grid = new Square[COLS][ROWS];
		Random nRandom = new Random(5);
         //set mines
		for (int i = 0; i < MINES; i++) {
			// TODO prevent repetitions
			int x = nRandom.nextInt(COLS);
			int y = nRandom.nextInt(ROWS);

			Square s = new Square();
			s.setMine(true);
			grid[x][y] = s;
		}

        // init empty squares
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; i < ROWS; i++) {
                Square s = grid[i][j];
				if (s == null) {
                    int neighbours = calculateNeighbours(grid, i, j);
                    s = new Square();
                    s.setNeighbours(neighbours);
                    grid[i][j] = s;
                }
			}
		}

	}

    private int calculateNeighbours(Square[][] grid, int i, int j) {
        int neighbours = 0;
        if (i - 1 >= 0 && grid[i-1][j] != null){
            neighbours++;
            if (j - 1 >= 0 && grid[i][j-1] != null){
                neighbours++;
                if (grid[i-1][j-1] != null) neighbours++;
            }
        }

        if (i + 1 <= COLS && grid[i+1][j] != null){
            neighbours++;
            if (j + 1 <= ROWS && grid[i][j+1] != null){
                neighbours++;
                if (grid[i+1][j+1] != null) neighbours++;
            }
        }
        return neighbours;
    }

    public void uncoverSquare(int x, int y) throws ExplosionException,
            NoSuchSquareException {
		if (x >= COLS || y >= ROWS) {
			throw new NoSuchSquareException();
		}
		if (grid[x][y].isMine())
			throw new ExplosionException();
		grid[x][y].uncover();
	}

	public void markSquare(int x, int y) {
		grid[x][y].mark();
	}

    private void
}
