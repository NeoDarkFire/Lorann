package model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovementTest {

	private Movement movement;
	
	@Before
	public void setUp() throws Exception {
		this.movement = new Movement(Direction.U);
	}

	@After
	public void tearDown() throws Exception {
		this.movement = null;
	}

	@Test
	public void testRotateLeft() {
		this.movement.rotateLeft();
		assertEquals(Direction.UL, this.movement.getDirection());
	}

	@Test
	public void testRotateRight() {
		this.movement.rotateRight();
		assertEquals(Direction.UR, this.movement.getDirection());
	}

	@Test
	public void testReverse() {
		this.movement.reverse();
		assertEquals(Direction.D, this.movement.getDirection());
	}

	@Test
	public void testSetDirectionByPoint() {
		this.movement.setDirection(new Point(1,1));
		assertEquals(Direction.DR, this.movement.getDirection());
	}

}
