package view;

import model.Direction;

/**
 * <h1>The Interface InputHandler.</h1>
 *
 */
public interface InputHandler {
	
	public Direction getInputDirection();
	public boolean isPressed(Action input);
	public boolean isReleased(Action input);
	public void press(Action input);
	public void release(Action input);
	public void updateInputs();
	

}
