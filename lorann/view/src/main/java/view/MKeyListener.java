package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * <h1>The Class MKeyListener enables to define if a key is pressed or released.</h1>
 *
 * @author aurel
 * @version 1.0
 */

public class MKeyListener extends KeyAdapter {
	
	/**
	 * Composition because we use the InputHandler in this class
	 */
	private InputHandler handler;
	/**
	 * Composition because we use the HashMap in this class
	 * HashMap of type action and boolean
	 */
	private HashMap<Integer, Action> keyMap;
	
	public MKeyListener(InputHandler handler, HashMap<Integer, Action> keyMap) {
		this.handler = handler;
		this.keyMap = keyMap;
	}

	/**
	 * Used when a key is pressed
	 * 
	 * @param e The KeyEvent.
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		if (this.keyMap.containsKey(e.getKeyCode())) {
			this.handler.press(this.keyMap.get(e.getKeyCode()));
		}
	}
	
	/**
	 * Used when a key is released
	 * 
	 * @param e The KeyEvent.
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		if (this.keyMap.containsKey(e.getKeyCode())) {
			this.handler.release(this.keyMap.get(e.getKeyCode()));
		}
	}
	

}