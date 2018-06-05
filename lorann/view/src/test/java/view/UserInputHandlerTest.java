package view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;

public class UserInputHandlerTest {

	private UserInputHandler handler;
	
	@Before
	public void setUp() {
		this.handler = new UserInputHandler();
	}

	@After
	public void tearDown() {
		this.handler = null;
	}
	
	@Test
	public void testGetInputDirection() {
		this.handler.press(Action.UP);
		this.handler.press(Action.LEFT);
		this.handler.updateInputs();
		assertEquals(Direction.UL, this.handler.getInputDirection());
	}
	
	@Test
	public void testIsPressed() {
		this.handler.press(Action.UP);
		this.handler.updateInputs();
		assertEquals(true, this.handler.isPressed(Action.UP));
	}
	
	@Test
	public void testIsReleased() {
		this.handler.press(Action.UP);
		this.handler.updateInputs();
		assertEquals(false, this.handler.isReleased(Action.UP));
	}
	
}
