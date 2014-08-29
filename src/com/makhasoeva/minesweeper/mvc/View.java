package com.makhasoeva.minesweeper.mvc;

public interface View {
    void drawModel(Model model);

    void addViewHandler(ActionType actionType, Runnable action);

    void setSquareHandler(SquareHandler handler);

    public enum ActionType {
        NewGame, HighScore, Exit, About
    }
}
