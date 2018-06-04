package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.SwingUtilities;

import model.ILevel;
import showboard.BoardFrame;
import showboard.ISquare;

/**
 * <h1>The Class Renderer allows to show the display.</h1>
 *
 * @author aurel
 * @version 1.0
 */

public class Renderer extends Observable implements Runnable{
	
	private ILevel level;
	private BoardFrame frame;
	private KeyListener keyListener;
	
	
	public Renderer() {
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * Set up the level
	 * Check if the frame is not null and do an update of the frame
	 * @param level
	 * 
	 */
	
	public void setLevel(ILevel level) {
		this.level = level;
		if (this.frame != null) {
			this.updateFrame();
		}
		
	}
	
	
	/**
	 * Set up the frame with the title "Lorann"
	 * Create a new BoardFrame
	 * 
	 */
	private void setupFrame() {
		this.frame = new BoardFrame("Lorann");
		this.updateFrame();
	}
	
	
	/**
	 * Run the setupFrame
	 * Show the frame
	 * 
	 */
	public void run() {
		this.setupFrame();
		this.frame.setVisible(true);
	}
	
	/**
	 * Add the sprite
	 * 
	 * @param sprite
	 */
	
	public void addSprite(Sprite sprite) {
		this.frame.addPawn(sprite);
	}
	
	
	/**
	 * Remove the sprite
	 * 
	 * @param sprite
	 * 
	 */
	public void removeSprite(Sprite sprite) {
		this.frame.removePawn(sprite);
	}
	
	public BoardFrame getFrame() {
		return this.frame;
		
	}
	/**
	 * Refresh
	 * Show and notify the different changes
	 */
	
	public void refresh() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Create the frame with different characteristics
	 * Like the width and the height
	 * Configure the display of the frame
	 * Get the coordinates of the Tile of the level
	 */
	private void updateFrame() {
		int width, height;
		width = (this.level != null) ? this.level.getWidth() : 1;
		height = (this.level != null) ? this.level.getHeight() : 1;
		this.frame.setDimension(new Dimension(width, height));
		this.frame.setDisplayFrame(new Rectangle(0, 0, width, height));
		this.frame.setSize(width*64, height*64);
		
		ISquare square;
		for (int y = 0; y < this.level.getHeight(); y++) {
			for (int x = 0; x < this.level.getWidth(); x++) {
				square = this.level.getTileAt(x, y);
				this.frame.addSquare(square, x, y);
			}
		}
		
		if (this.keyListener != null && this.frame.getKeyListeners()[0] != this.keyListener) {
			this.frame.addKeyListener(this.keyListener);
		}
	}
	/**
	 * Set the keyListener
	 * @param keyListener
	 */
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
