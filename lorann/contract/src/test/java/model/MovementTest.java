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
		assertEquals(this.movement.getDirection(), Direction.UL);
	}

	@Test
	public void testRotateRight() {
		this.movement.rotateRight();
		assertEquals(this.movement.getDirection(), Direction.UR);
	}

	@Test
	public void testReverse() {
		this.movement.reverse();
		assertEquals(this.movement.getDirection(), Direction.D);
	}

	@Test
	public void testSetDirectionByPoint() {
		this.movement.setDirection();
		assertEquals(this.movement.getDirection(), Direction.DR);
	}

}
