package com.makhasoeva.minesweeper.mvc;

public abstract class Controller<M extends Model, V extends View<M>>{

    private M model;
    private V view;

    //protected??
    public abstract void onNewGame();
    public abstract void onAbout();
    public abstract void onExit();
    public abstract void onHighScores();


    public abstract void onSquareUncovered(int x, int y);
 //   public abstract void onSquareMarked();

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
       // view.setModel(model);
    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public abstract V createView() ;

}
