package model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <h1>Tests the movements</h1>
 *
 */
public class MovementTest {

	/**
	 * the movement the entity wants to do
	 */
	private Movement movement;
	
	@Before
	public void setUp() throws Exception {
		this.movement = new Movement(Direction.U);
	}

	@After
	public void tearDown() throws Exception {
		this.movement = null;
	}

	/**
	 * tests if the entity rotate correctly
	 */
	@Test
	public void testRotateLeft() {
		this.movement.rotateLeft();
		assertEquals(Direction.UL, this.movement.getDirection());
	}

	/**
	 * tests if the entity rotate correctly
	 */
	@Test
	public void testRotateRight() {
		this.movement.rotateRight();
		assertEquals(Direction.UR, this.movement.getDirection());
	}

	/**
	 * tests if the entity rotate correctly
	 */
	@Test
	public void testReverse() {
		this.movement.reverse();
		assertEquals(Direction.D, this.movement.getDirection());
	}

	/**
	 * tests if we get the good directionby vector
	 */
	@Test
	public void testSetDirectionByPoint() {
		this.movement.setDirection(new Point(1,1));
		assertEquals(Direction.DR, this.movement.getDirection());
	}

}
