package com.makhasoeva.minesweeper.mvc;

public interface SquareHandler {
    void onSquareOpened(int i, int j);

    void onSquareMarked(int i, int j);
}
