package view;

import java.util.HashMap;
import java.util.LinkedList;

import model.Direction;

/**
 * <h1>The Class UserInputHandler is used to associate the Action (key used) of the user and the direction of the player in the game.</h1>
 *
 * 
 * @version 1.0
 */

public class UserInputHandler implements InputHandler{
	
	private HashMap<Action, Boolean> inputMap;
	private HashMap<Action, LinkedList<Boolean>> llist;
	
	
	public UserInputHandler() {
		this.inputMap = new HashMap<>();
		this.llist = new HashMap<>();
		for (final Action input : Action.values()) {
			this.inputMap.put(input, false);
			this.llist.put(input, new LinkedList<>());
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
		return this.inputMap.get(input);
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
		return !this.inputMap.get(input);
	}
	
	
	/**
	 * If boolean of isPressed is true, execute press
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action pressed?
	 */
	
	public void press(Action input) {
		this.llist.get(input).clear();
		this.llist.get(input).addFirst(true);
	}
	
	
	/**
	 * If boolean of isReleased is true, execute released
	 * 
	 * @param input Action to check.
	 * 
	 * @return Is the action released?
	 */
	public void release(Action input) {
		this.llist.get(input).addLast(false);
	}
	
	/**
	 * Update the state of the inputs to use the top of the stack.
	 */
	public void updateInputs() {
		LinkedList<Boolean> llist;
		for (final Action input : Action.values()) {
			llist = this.llist.get(input);
			if (!llist.isEmpty()) {
				this.inputMap.put(input, llist.pollFirst());
			}
		}
	}

}
