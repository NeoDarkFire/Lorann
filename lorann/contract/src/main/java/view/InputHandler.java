package view;

import model.Direction;

public interface InputHandler {
	
	public Direction getInputDirection();
	public boolean isPressed(Action input);
	public boolean isReleased(Action input);
	public void press(Action input);
	public void release(Action input);
	public void updateInputs();
	

}
