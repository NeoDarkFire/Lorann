package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * <h1>The Class MKeyListener enables to define if a key is pressed or released.</h1>
 *
 * 
 * @version 1.0
 */

public class MKeyListener extends KeyAdapter {
	
	private InputHandler handler;
	private HashMap<Integer, Action> keyMap;
	
	public MKeyListener(InputHandler handler, HashMap<Integer, Action> keyMap) {
		this.handler = handler;
		this.keyMap = keyMap;
	}

	/**
	 * Used when a key is pressed
	 * 
	 * @param the keyEvent
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
	 * @param the keyEvent
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		if (this.keyMap.containsKey(e.getKeyCode())) {
			this.handler.release(this.keyMap.get(e.getKeyCode()));
		}
	}
	

}