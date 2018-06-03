package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Movement;

public class TowerAISystemTest {

	private TowerAISystem sys;
	
	@Before
	public void setUp() throws Exception {
		sys = new TowerAISystem();
	}

	@After
	public void tearDown() throws Exception {
		sys = null;
	}

	@Test
	public void testGetMoveOrder1() {
		final Movement[] moves = sys.getMoveOrder(4, 1);
		assertEquals(Direction.R, moves[0].getDirection());
		assertEquals(Direction.D, moves[1].getDirection());
		assertEquals(Direction.U, moves[2].getDirection());
		assertEquals(Direction.L, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder2() {
		final Movement[] moves = sys.getMoveOrder(-8, -7);
		assertEquals(Direction.L, moves[0].getDirection());
		assertEquals(Direction.U, moves[1].getDirection());
		assertEquals(Direction.D, moves[2].getDirection());
		assertEquals(Direction.R, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder3() {
		final Movement[] moves = sys.getMoveOrder(1, -5);
		assertEquals(Direction.U, moves[0].getDirection());
		assertEquals(Direction.R, moves[1].getDirection());
		assertEquals(Direction.L, moves[2].getDirection());
		assertEquals(Direction.D, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder4() {
		final Movement[] moves = sys.getMoveOrder(-2, 3);
		assertEquals(Direction.D, moves[0].getDirection());
		assertEquals(Direction.L, moves[1].getDirection());
		assertEquals(Direction.R, moves[2].getDirection());
		assertEquals(Direction.U, moves[3].getDirection());
	}

}
