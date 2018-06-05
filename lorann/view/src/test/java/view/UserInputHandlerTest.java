package view;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;

/**
 * <h1>These are different tests for the view</h1>
 * @author aurel
 *
 */
public class UserInputHandlerTest {

	private UserInputHandler handler;
	private HashMap<Action, Boolean> inputMap;
	
	@Before
	public void setUp() {
		this.handler = new UserInputHandler();
	}

	@After
	public void tearDown() {
		this.handler = null;
	}
	/**
	 * This test, test the keyboard keys according to the direction
	 * "assertEquals" enables to check the equality between two items
	 */
	@Test
	public void testGetInputDirection() {
		this.handler.press(Action.UP);
		this.handler.press(Action.LEFT);
		assertEquals(this.handler.getInputDirection(), Direction.UL);
	}
	
	/**
	 * This test, check if a keyboard key is pressed
	 * "assertEquals" enables to check the equality between two items
	 * 
	 */
	@Test
	public void testIsPressed() {
		this.handler.press(Action.UP);
		assertEquals(this.handler.isPressed(Action.UP), true);
	}
	
	/**
	 * This test, check if a keyboard key is released
	 *"assertEquals" enables to check the equality between two items
	 *
	 */
	@Test
	public void testIsReleased() {
		this.handler.press(Action.UP);
		assertEquals(this.handler.isReleased(Action.UP), false);
	}
	
}
