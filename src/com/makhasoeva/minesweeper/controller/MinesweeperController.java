package com.makhasoeva.minesweeper.controller;

import com.makhasoeva.minesweeper.View.MinesweeperView;
import com.makhasoeva.minesweeper.mvc.Controller;
import com.makhasoeva.minesweeper.mvc.Model;

import javax.swing.*;

/**
 * Created by Oli on 28.08.2014.
 */
public class MinesweeperController {

    public static void main(String[] args) {
        Model model = new Model();
        MinesweeperView view = new MinesweeperView(new JFrame());
        Controller controller = new Controller(model, view);
        controller.startGame();
    }
}
