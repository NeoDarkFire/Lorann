package view;

import model.Direction;

public interface InputHandler {
	
	Direction getInputDirection();
	boolean isPressed(Action input);
	boolean isReleased(Action input);
	void press(Action input);
	void release(Action input);
	void updateInputs();

}
