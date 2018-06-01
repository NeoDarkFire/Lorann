package view;

import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Direction;
import model.ILevel;

/**
 * <h1>The Class ViewFacade provides a facade of the View component.</h1>
 *
 * 
 * @version 1.0
 */
public class ViewFacade implements IView {
	
	private Renderer renderer;
	private HashMap<Entity, Sprite> sprites;
	private InputHandler handler;

    /**
     * Instantiates a new view facade.
     */
    public ViewFacade() {
        super();
    }
    
    
    /**
	 * Add the Sprite
	 * 
	 * @param entity
	 * 
	 */
    @Override
    public void addSprite(Entity entity) {
    	
    }
    
    /**
	 * Remove the Sprite
	 * 
	 * @param entity
	 * 
	 */
    @Override
    public void removeSprite(Entity entity) {
    	
    }
    
    
    /**
	 * Displays the level
	 * 
	 * @param level
	 * 
	 */
    @Override
    public void displayLevel(ILevel level) {
    	
    }
    
    
    /**
	 * Use a boolean to show if the key is pressed
	 * 
	 * @param input Action to check
	 * 
	 * @return Is the action pressed
	 * 
	 */
    @Override
    public boolean isPressed(Action input) {
    	return false;
    }
    
    
    /**
	 * Use a boolean to show if the key is released
	 * 
	 * @param input Action to check
	 * 
	 * @return Is the action released
	 * 
	 */
    @Override
    public boolean isReleased(Action input) {
    	return false;
    }
    
    
    /**
	 * Recover the direction if the key is pressed
	 * 
	 */
    @Override
    public Direction getInputDirection() {
    	return null;
    }
    
    @Override
    public void refresh() {
    	
    }

}
