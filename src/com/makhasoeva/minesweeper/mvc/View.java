package com.makhasoeva.minesweeper.mvc;

public abstract class View {
	private Model model;

	public abstract View initializeNew();

	public View() {
		initializeNew();
	}

	public void buildMenu() {

	}

}
