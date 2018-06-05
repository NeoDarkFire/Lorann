package view;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import ecs.Entity;
import model.Direction;
import model.ILevel;

/**
 * <h1>The Class ViewFacade provides a facade of the View component.</h1>
 *
 * @author aurel
 * @version 1.0
 */
public class ViewFacade implements IView {
	
	/**
	 * Composition because we use the Renderer in this class
	 */
	private Renderer renderer;
	/**
	 * Composition because we use the HashMap in this class
	 * HashMap of type entity and sprite
	 */
	private HashMap<Entity, Sprite> sprites;
	/**
	 * Composition because we use the InputHandler in this class
	 */
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
	 * @param entity The Entity.
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
	 * @param entity The Entity.
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
	 * @param level The Level.
	 * 
	 */
    @Override
    public void setLevel(ILevel level) {
    	this.renderer.setLevel(level);
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
    
    /**
     * If multiple events have been registered, use the first one and pop it from the queue
     */
    @Override
    public void updateInputs() {
    	this.handler.updateInputs();
    }

}
