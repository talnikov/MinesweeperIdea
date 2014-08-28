package com.makhasoeva.minesweeper.Model;

import com.makhasoeva.minesweeper.mvc.Model;

/**
 * Created by Oli on 28.08.2014.
 */
public class MinesweeperModel extends Model {
    private Board board;

    public void initializeBoard(){
        board = new Board();
    }

    public MinesweeperModel(){
        initializeBoard();
        setModificationStatus(false);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
