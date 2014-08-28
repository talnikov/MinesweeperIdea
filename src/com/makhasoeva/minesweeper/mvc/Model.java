package com.makhasoeva.minesweeper.mvc;

public class Model {

	private boolean modified = false;

	public boolean isModified() {
		return modified;
	}

	public void setModificationStatus(boolean status) {
		modified = status;
	}

    public Model initializeNew() {
        return new Model();
    }

}
