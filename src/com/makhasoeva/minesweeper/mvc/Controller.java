package com.makhasoeva.minesweeper.mvc;

public abstract class Controller {

    public abstract void onNewGame();
    public abstract void onAbout();
    public abstract void onExit();
    public abstract void onHighScores();


    public abstract void onSquareUncovered();
    public abstract void onSquareMarked();

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private Model model;
    private View view;

}
