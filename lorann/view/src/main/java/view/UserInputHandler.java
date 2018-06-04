package view;

import java.util.HashMap;

import model.Direction;

/**
 * <h1>The Class UserInputHandler is used to associate the Action (key used) of the user and the direction of the player in the game.</h1>
 *
 * 
 * @version 1.0
 */

public class UserInputHandler implements InputHandler{
	
	private HashMap<Action, Boolean> inputMap;
	
	
	public UserInputHandler() {
		this.inputMap = new HashMap<>();
		for (final Action input : Action.values()) {
			this.inputMap.put(input, false);
		}
	}
	
	public Direction getInputDirection() {
		if (this.isPressed(Action.UP)) {
			if (this.isPressed(Action.LEFT)) {
				return Direction.UL;
			}
			else if (this.isPressed(Action.RIGHT)) {
				return Direction.UR;
			}
			else {
				return Direction.U;
			}
		}
		else if (this.isPressed(Action.DOWN)) {
			if (this.isPressed(Action.LEFT)) {
				return Direction.DL;
			}
			else if (this.isPressed(Action.RIGHT)) {
				return Direction.DR;
			}
			else {
				return Direction.D;
			}
		}
		else {
			if (this.isPressed(Action.LEFT)) {
				return Direction.L;
			}
			else if (this.isPressed(Action.RIGHT)) {
				return Direction.R;
			}
			else {
				return Direction.NONE;
			}
		}
	}
	
	/**
	 * Use a boolean to show if the key is pressed
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action pressed?
	 * 
	 */
	
	public boolean isPressed(Action input) {
		return (this.inputMap.get(input) == true);
	}
	
	/**
	 * Use a boolean to show if the key is released
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action released?
	 * 
	 */
	
	public boolean isReleased(Action input) {
		return (this.inputMap.get(input) == false);
	}
	
	
	/**
	 * If boolean of isPressed is true, execute press
	 * 
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action pressed?
	 */
	
	public void press(Action input) {
		this.inputMap.put(input, true);
	}
	
	
	/**
	 * If boolean of isReleased is true, execute released
	 * 
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action released?
	 */
	public void release(Action input) {
		this.inputMap.put(input, false);
	}

}
