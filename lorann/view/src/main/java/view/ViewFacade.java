package view;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import ecs.Entity;
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
	
	static private HashMap<Integer, Action> keyMap = new HashMap<>();
	static {
		keyMap.put(KeyEvent.VK_LEFT, Action.LEFT);
		keyMap.put(KeyEvent.VK_RIGHT, Action.RIGHT);
		keyMap.put(KeyEvent.VK_UP, Action.UP);
		keyMap.put(KeyEvent.VK_DOWN, Action.DOWN);
		keyMap.put(KeyEvent.VK_SPACE, Action.SPELL);
	}

    /**
     * Instantiates a new view facade.
     */
    public ViewFacade() {
        this.renderer = new Renderer();
        this.handler = new UserInputHandler();
        this.renderer.setKeyListener(new MKeyListener(this.handler, keyMap));
        this.sprites = new HashMap<>();
    }
    
    
    /**
	 * Add the Sprite
	 * 
	 * @param entity
	 * 
	 */
    @Override
    public void addSprite(Entity entity) {
    	this.sprites.put(entity, new Sprite(entity));
    	this.renderer.addSprite(this.sprites.get(entity));
    }
    
    /**
	 * Remove the Sprite
	 * 
	 * @param entity
	 * 
	 */
    @Override
    public void removeSprite(Entity entity) {
    	this.renderer.removeSprite(this.sprites.get(entity));
    	this.sprites.remove(entity);
    	
    }
    
    
    /**
	 * Displays the level
	 * 
	 * @param level
	 * 
	 */
    @Override
    public void setLevel(ILevel level) {
    	this.renderer.setLevel(level);
    	this.renderer.refresh();
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
    	return (this.handler.isPressed(input));
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
    	return (this.handler.isReleased(input));
    }
    
    
    /**
	 * Recover the direction if the key is pressed
	 * 
	 */
    @Override
    public Direction getInputDirection() {
    	return (this.handler.getInputDirection());
    }
    
    /**
     * Refreshes the screen.
     */
    @Override
    public void refresh() {
    	this.renderer.refresh();
    }

}
