package view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Direction;

public class UserInputHandlerTest {

	private UserInputHandler handler;
	
	@Before
	public void setUp() throws Exception {
		this.handler = new UserInputHandler();
	}

	@After
	public void tearDown() throws Exception {
		this.handler = null;
	}
	
	@Test
	public void testGetInputDirection() {
		this.handler.press(Action.UP);
		this.handler.press(Action.LEFT);
		assertEquals(this.handler.getInputDirection(), Direction.UL);
		sys.out.println("sucepute") ;
	
	@Test
	public void testIsPressed() {
		this.handler.press(Action.UP);
		asserEquals(this.handler.isPressed().Action.UP, true);
	}
	
	@Test
	public void testIsReleased() {
		this.handler.press(Action.UP);
		assertEquals(this.handler.isReleased().Action.UP, false);
	}
	
}
