package com.makhasoeva.minesweeper.Model;

import java.util.HashSet;
import java.util.Random;

public class Board {
    public static final int MINES = 10;
    public static final int COLS = 9;
    public static final int ROWS = 9;

    private Square[][] grid = new Square[COLS][ROWS];

    public Square[][] getGrid() {
        return grid;
    }

    public Board() {

        for (int i = 0; i < COLS; i++) {
            grid[i] = new Square[COLS];
            for (int j = 0; j < ROWS; j++) {
                grid[i][j] = new Square();
            }
        }
        HashSet<GridPoint> mineCoords = generateMinesPlaces();
        for (GridPoint point: mineCoords){
            grid[(int)point.getX()][(int)point.getY()] = new Square();
            grid[(int) point.getX()][(int)point.getY()].setMine(true);
        }

        // init neighbours
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (!grid[i][j].isMine()) {
                    int neighbours = calculateNeighbours(grid, i, j);
                    grid[i][j].setNeighbours(neighbours);
                }
            }
        }
    }

    private int calculateNeighbours(Square[][] grid, int i, int j) {
        int neighbours = 0;
        if (i - 1 >= 0 && grid[i - 1][j].isMine()) neighbours++;
        if (j - 1 >= 0 && grid[i][j - 1].isMine()) neighbours++;
        if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1].isMine()) neighbours++;

        if (i + 1 < COLS && grid[i + 1][j].isMine()) neighbours++;
        if (j + 1 < ROWS && grid[i][j + 1].isMine()) neighbours++;
        if (i + 1 < COLS && j + 1 < ROWS && grid[i + 1][j + 1].isMine()) neighbours++;

        if (i + 1 < COLS && j - 1 >= 0 && grid[i + 1][j - 1].isMine()) neighbours++;
        if (i - 1 >= 0 && j + 1 < ROWS && grid[i - 1][j + 1].isMine()) neighbours++;
        return neighbours;
    }

    public void uncoverSquare(int x, int y) throws ExplosionException{
        if (grid[x][y].isMine())
            throw new ExplosionException();
        grid[x][y].uncover();
    }

    public void markSquare(int x, int y) {
        grid[x][y].mark();
    }

    private HashSet<GridPoint> generateMinesPlaces() {
        HashSet<GridPoint> coords = new HashSet<GridPoint>();
        Random nRandom = new Random();
        while (coords.size() != MINES) {
            GridPoint p = new GridPoint(nRandom.nextInt(COLS), nRandom.nextInt(ROWS));
            coords.add(p);
        }
        return coords;
    }


}


