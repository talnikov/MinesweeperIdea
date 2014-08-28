package com.makhasoeva.minesweeper.controller;

import com.makhasoeva.minesweeper.Model.MinesweeperModel;
import com.makhasoeva.minesweeper.View.MinesweeperView;
import com.makhasoeva.minesweeper.mvc.Controller;
import com.makhasoeva.minesweeper.mvc.Model;
import com.makhasoeva.minesweeper.mvc.View;

import javax.swing.*;

/**
 * Created by Oli on 28.08.2014.
 */
public class MinesweeperController extends Controller<MinesweeperModel, MinesweeperView> {
    @Override
    public void onNewGame() {
//        if (getModel().isModified() && !confirmLosingModifications()){
//            return;
//        }
        setModel(new MinesweeperModel());
        getModel().setModificationStatus(false);
        getView().setModel(getModel());
    }
    protected void setMenuHandlers() {
        getView().addViewHandler(View.ActionType.NewGame, new Runnable() {
            @Override
            public void run() {
                onNewGame();
            }
        });
    }
    public boolean confirmLosingModifications() {
        return getView().showConfirmationDialog();
    }
    @Override
    public void onAbout() {


    }

    @Override
    public void onExit() {
        if (getModel().isModified() && !confirmLosingModifications()){
            return;
        }
        //TODO
    }

    @Override
    public void onHighScores() {

    }

    @Override
    public void onSquareUncovered(int x, int y) {

    }



//    @Override
//    public void onSquareMarked() {
//
//    }

    @Override
    public MinesweeperView createView() {
        setModel(new MinesweeperModel());
        setView(new MinesweeperView(new JFrame(), getModel()));
        setModel(getView().getModel());
        return getView();
    }

    public static void main(String[] args) {
        MinesweeperController ms = new MinesweeperController();

     //   ms.setMenuHandlers();
        ms.createView();
       ms.setMenuHandlers();
      //  ms.getView().getFrame().setVisible(true);

    }
}
