package view;

import java.util.HashMap;

import javax.swing.JOptionPane;

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
    public void addSprite(Entity entity) {
    	
    }
    
    /**
	 * Remove the Sprite
	 * 
	 * @param entity
	 * 
	 */
    public void removeSprite(Entity entity) {
    	
    }
    
    
    /**
	 * Displays the level
	 * 
	 * @param level
	 * 
	 */
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
    public boolean isPressed(Action input) {
    	
    }
    
    
    /**
	 * Use a boolean to show if the key is released
	 * 
	 * @param input Action to check
	 * 
	 * @return Is the action released
	 * 
	 */
    public boolean isReleased(Action input) {
    	
    }
    
    
    /**
	 * Recover the direction if the key is pressed
	 * 
	 */
    public Direction getInputDirection() {
    	
    }
    
    public void refresh() {
    	
    }

    /*
     * (non-Javadoc)
     * @see view.IView#displayMessage(java.lang.String)
     */
    @Override
    public final void displayMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
