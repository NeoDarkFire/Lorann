package model;

import java.awt.Image;

import showboard.ISquare;

/**
 * <h1>The Interface ITile.</h1>
 *
 */
public interface ITile extends ISquare {
	
	public Image getImage();
	public TileSolidity getSolidity();
	

}
