package com.makhasoeva.minesweeper.mvc;

import java.io.Console;

public abstract class View<M extends Model> {
	private M model;

    public abstract void drawModel(M model);
    public abstract void addViewHandler(ActionType actionType, Runnable action);

    public void setModel(M model) {
        this.model = model;
      //  drawModel(model);
    }

    public M getModel() {
        return model;
    }

    public abstract void initializeNewModel();

    public abstract boolean showConfirmationDialog();

    public enum ActionType {
        NewGame, HighScore, Exit, About
    }
}
