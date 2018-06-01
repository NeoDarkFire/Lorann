package view;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;

public class UserInputHandlerTest {

	private UserInputHandler handler;
	private HashMap<Action, Boolean> inputMap;
	
	@Before
	public void setUp() {
		this.inputMap = new HashMap<>();
		this.handler = new UserInputHandler(this.inputMap);
	}

	@After
	public void tearDown() {
		this.handler = null;
	}
	
	@Test
	public void testGetInputDirection() {
		this.handler.press(Action.UP);
		this.handler.press(Action.LEFT);
		assertEquals(this.handler.getInputDirection(), Direction.UL);
	}
	
	@Test
	public void testIsPressed() {
		this.handler.press(Action.UP);
		assertEquals(this.handler.isPressed(Action.UP), true);
	}
	
	@Test
	public void testIsReleased() {
		this.handler.press(Action.UP);
		assertEquals(this.handler.isReleased(Action.UP), false);
	}
	
}
