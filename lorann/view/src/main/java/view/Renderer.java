package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.Observable;

import model.ILevel;
import showboard.BoardFrame;
import showboard.ISquare;

/**
 * <h1>The Class Renderer allows to show the display.</h1>
 *
 * @author aurel
 * @version 1.0
 */

public class Renderer extends Observable implements Runnable {
	
	/**
	 * Composition because we use the level in this class
	 */
	private ILevel level;
	/**
	 * Composition because we use the frame in this class
	 */
	private BoardFrame frame;
	/**
	 * Composition because we use the keyListener in this class
	 */
	private KeyListener keyListener;
	
	
	public Renderer() {
		super();
	}
	
	/**
	 * Set up the level
	 * 
	 * @param level The Level.
	 * 
	 */
	
	public void setLevel(ILevel level) {
		this.level = level;
		if (this.frame == null) {
			run();
		}
		this.updateFrame();
		
	}
	
	private void setupFrame() {
		this.frame = new BoardFrame("Lorann");
		this.updateFrame();
	}
	
	public void run() {
		this.setupFrame();
		this.frame.setVisible(true);
		this.addObserver(this.frame.getObserver());
	}
	
	/**
	 * Add the sprite
	 * 
	 * @param sprite The Sprite.
	 */
	
	public void addSprite(Sprite sprite) {
		this.frame.addPawn(sprite);
	}
	
	
	/**
	 * Remove the sprite
	 * 
	 * @param sprite The Sprite.
	 * 
	 */
	public void removeSprite(Sprite sprite) {
		this.frame.removePawn(sprite);
	}
	
	public BoardFrame getFrame() {
		return this.frame;
		
	}
	
	public void refresh() {
		synchronized (this.frame) {
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	private void updateFrame() {
		int width, height;
		width = this.level.getWidth();
		height = this.level.getHeight();
		this.frame.setDimension(new Dimension(width, height));
		this.frame.setDisplayFrame(new Rectangle(0, 0, width, height));
		this.frame.setSize(width*64, height*64);
		
		ISquare square;
		synchronized (this.frame) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					square = this.level.getTileAt(x, y);
					this.frame.addSquare(square, x, y);
				}
			}
		}
		
		if (this.keyListener != null && this.frame.getKeyListeners().length == 0) {
			this.frame.addKeyListener(this.keyListener);
		}
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
