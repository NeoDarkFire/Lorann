package view;

import model.Direction;

/**
 * <h1>The Interface InputHandler.</h1>
 *
 */
public interface InputHandler {
	
	Direction getInputDirection();
	boolean isPressed(Action input);
	boolean isReleased(Action input);
	void press(Action input);
	void release(Action input);
	void updateInputs();

}
