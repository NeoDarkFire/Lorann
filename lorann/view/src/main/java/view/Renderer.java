package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;

import javax.swing.SwingUtilities;

import model.ILevel;
import showboard.BoardFrame;
import showboard.ISquare;

/**
 * <h1>The Class Renderer allows to show the display.</h1>
 *
 * 
 * @version 1.0
 */

public class Renderer extends Observable implements Runnable{
	
	private ILevel level;
	private BoardFrame frame;
	
	
	public Renderer(ILevel level) {
		this.setLevel(level);
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * Set up the level
	 * 
	 * @param level
	 * 
	 */
	
	public void setLevel(ILevel level) {
		this.level = level;
		if (this.frame != null) {
			this.updateFrame();
		}
		
	}
	
	private void setupFrame() {
		int width, height;
		width = this.level.getWidth();
		height = this.level.getHeight();
		this.frame = new BoardFrame("Lorann");
		this.frame.setDimension(new Dimension(width, height));
		this.frame.setDisplayFrame(new Rectangle(0,0,width, height));
		this.frame.setSize(width*64, height*64);
		this.frame.setVisible(true);
		this.updateFrame();
	}
	
	public void run() {
		this.setupFrame();
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
	
	public void refresh() {
		this.setChanged();
		this.notifyObservers();
	}
	
	private void updateFrame() {
		ISquare square;
		for (int y = 0; y < this.level.getHeight(); y++) {
			for (int x = 0; x < this.level.getWidth(); x++) {
				square = this.level.getTileAt(x, y);
				this.frame.addSquare(square, x, y);
			}
		}
	}
}
