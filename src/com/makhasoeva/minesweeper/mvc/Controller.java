package com.makhasoeva.minesweeper.mvc;

public abstract class Controller {

	private Model model;
	private View view;

	public Model initModel() {
		return model.initializeNew();
	}

	public View initView() {
		return view.initializeNew();
	}
}
