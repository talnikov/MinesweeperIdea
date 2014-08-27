package com.makhasoeva.minesweeper.Model;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.makhasoeva.minesweeper.Model.Square;

public class SquareTest {

	private Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square();
	}

	@After
	public void tearDown() throws Exception {
		square = null;
	}

	@Test
	public void testMine() throws Exception {
		square.setMine(true);
		assertTrue(square.isMine());
	}

	@Test
	public void testUncoverCoveredAction() throws Exception {
		square.setState(Square.States.COVERED);
		square.uncover();
		assertTrue(square.getState() == Square.States.UNCOVERED);
	}

	@Test
	public void testUncoverUncoveredAction() throws Exception {
		square.setState(Square.States.UNCOVERED);
		square.uncover();
		assertTrue(square.getState() == Square.States.UNCOVERED);
	}

	@Test
	public void testUncoverMarkedAction() throws Exception {
		square.setState(Square.States.MARKED);
		square.uncover();
		assertTrue(square.getState() == Square.States.MARKED);
	}

	@Test
	public void testMarkCoveredAction() throws Exception {
		square.setState(Square.States.COVERED);
		square.mark();
		assertTrue(square.getState() == Square.States.MARKED);
	}

	@Test
	public void testMarkUncoveredAction() throws Exception {
		square.setState(Square.States.UNCOVERED);
		square.mark();
		assertTrue(square.getState() == Square.States.UNCOVERED);
	}

	@Test
	public void testMarkMarkedAction() throws Exception {
		square.setState(Square.States.MARKED);
		square.mark();
		assertTrue(square.getState() == Square.States.COVERED);
	}
}
