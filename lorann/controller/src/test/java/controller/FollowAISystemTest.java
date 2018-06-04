package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Movement;

public class FollowAISystemTest {

	private FollowAISystem sys;
	
	@Before
	public void setUp() throws Exception {
		sys = new FollowAISystem();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMoveOrder() {
		final Movement[] moves = sys.getMoveOrder(0, -1);
		assertEquals(Direction.U, moves[0].getDirection());
		assertEquals(Direction.UL, moves[1].getDirection());
		assertEquals(Direction.UR, moves[2].getDirection());
		assertEquals(Direction.L, moves[3].getDirection());
		assertEquals(Direction.R, moves[4].getDirection());
		assertEquals(Direction.DL, moves[5].getDirection());
		assertEquals(Direction.DR, moves[6].getDirection());
		assertEquals(Direction.D, moves[7].getDirection());
	}

}
