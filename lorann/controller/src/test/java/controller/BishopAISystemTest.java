package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Movement;

public class BishopAISystemTest {
	
	private BishopAISystem sys;

	@Before
	public void setUp() throws Exception {
		sys = new BishopAISystem();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMoveOrder1() {
		final Movement[] moves = sys.getMoveOrder(4, 1);
		assertEquals(Direction.DR, moves[0].getDirection());
		assertEquals(Direction.UR, moves[1].getDirection());
		assertEquals(Direction.DL, moves[2].getDirection());
		assertEquals(Direction.UL, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder2() {
		final Movement[] moves = sys.getMoveOrder(-8, -7);
		assertEquals(Direction.UL, moves[0].getDirection());
		assertEquals(Direction.DL, moves[1].getDirection());
		assertEquals(Direction.UR, moves[2].getDirection());
		assertEquals(Direction.DR, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder3() {
		final Movement[] moves = sys.getMoveOrder(1, -5);
		assertEquals(Direction.UR, moves[0].getDirection());
		assertEquals(Direction.UL, moves[1].getDirection());
		assertEquals(Direction.DR, moves[2].getDirection());
		assertEquals(Direction.DL, moves[3].getDirection());
	}
	
	@Test
	public void testGetMoveOrder4() {
		final Movement[] moves = sys.getMoveOrder(-2, 3);
		assertEquals(Direction.DL, moves[0].getDirection());
		assertEquals(Direction.DR, moves[1].getDirection());
		assertEquals(Direction.UL, moves[2].getDirection());
		assertEquals(Direction.UR, moves[3].getDirection());
	}

}
