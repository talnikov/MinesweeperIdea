package com.makhasoeva.minesweeper.Model;

public class Square {
	public enum States {
		COVERED, UNCOVERED, MARKED
	}

	private States state;

	public void setState(States state) {
		this.state = state;
	}

	private boolean mine;

    private Integer neighbours;

    public Integer getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Integer neighbours) {
        this.neighbours = neighbours;
    }

    @Override

	public String toString() {
		return "Square [state=" + state + ", mine=" + mine + "]";
	}

	public boolean isMine() {
		return mine;
	}

	public Square() {
		state = States.COVERED;
		mine = false;
        neighbours = null;
	}

	public void setMine(boolean b) {
		mine = b;
	}

	public void uncover() {
		if (state == States.COVERED)
			setState(States.UNCOVERED);
		return;
	}

	public States getState() {
		return state;
	}

	public void mark() {
		switch (state) {
		case COVERED:
			setState(States.MARKED);
			break;
		case MARKED:
			setState(States.COVERED);
			break;
		default:
			break;
		}
	}

}
