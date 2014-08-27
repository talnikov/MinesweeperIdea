package com.makhasoeva.minesweeper.mvc;

public abstract class Model {

	private boolean modified = false;

	public boolean isModified() {
		return modified;
	}

	public void setModificationStatus(boolean status) {
		modified = status;
	}

	public Model() {
		initializeNew();
	}

	protected abstract Model initializeNew();

}
