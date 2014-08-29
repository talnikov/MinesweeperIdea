package com.makhasoeva.minesweeper.mvc;

import com.makhasoeva.minesweeper.Model.ExplosionException;

public class Controller {

    private final Model model;
    private final View view;

    private final SquareHandler squareHandler = new SquareHandler() {
        @Override
        public void onSquareOpened(int i, int j) {
            try {
                model.uncoverSquare(i, j);
            } catch (ExplosionException e) {
                // TODO handle explosion
            }
            // TODO recursively uncover the neighbors
            view.drawModel(model);
        }

        @Override
        public void onSquareMarked(int i, int j) {
            model.markSquare(i, j);
            view.drawModel(model);
        }
    };

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        setupHandlers();
        // TODO tell the view to show up
    }

    private void setupHandlers() {
        view.addViewHandler(View.ActionType.NewGame, new Runnable() {
            @Override
            public void run() {
                onNewGame();
            }
        });
        view.setSquareHandler(squareHandler);
        // TODO other actions
    }

    private void onNewGame() {
        model.reset();
        view.drawModel(model);
    }
}
