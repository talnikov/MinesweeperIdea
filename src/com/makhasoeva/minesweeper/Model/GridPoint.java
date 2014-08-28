package com.makhasoeva.minesweeper.Model;

/**
* Created by Oli on 28.08.2014.
*/
public class GridPoint {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridPoint)) return false;

        GridPoint that = (GridPoint) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
