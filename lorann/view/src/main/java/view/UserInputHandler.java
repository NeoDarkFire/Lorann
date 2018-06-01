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
	
	
	public UserInputHandler(HashMap<Action, Boolean> inputMap) {
		this.inputMap = inputMap;
	}
	
	public Direction getInputDirection() {
		return null;
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
		return false;
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
		return false;
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
		
	}

}
