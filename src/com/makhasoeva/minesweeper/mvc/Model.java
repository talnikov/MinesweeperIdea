package com.makhasoeva.minesweeper.mvc;

import com.makhasoeva.minesweeper.Model.Board;
import com.makhasoeva.minesweeper.Model.ExplosionException;
import com.makhasoeva.minesweeper.Model.Square;

public class Model {

	private boolean modified = false;
    private Board board;

    public Model() {
        // Don't call reset() directly as calling
        // overridable methods from the constructor is an evil did
        doReset();
    }

    private final void doReset() {
        board = new Board();
        modified = false;
    }

    public void reset() {
        doReset();
    }

    public boolean isModified() {
		return modified;
	}

    public void uncoverSquare(int i, int j) throws ExplosionException {
        board.uncoverSquare(i, j);
        modified = true;
    }

    public void markSquare(int i, int j) {
        board.markSquare(i, j);
        // TODO update modified?
    }

    public Square.States getSquareState(int x, int y) {
        return board.getGrid()[x][y].getState();
    }

    public int getSquareNeighbors(int x, int y) {
        return board.getGrid()[x][y].getNeighbours();
    }

}
