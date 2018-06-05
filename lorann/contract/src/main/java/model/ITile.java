package model;

import java.awt.Image;

import showboard.ISquare;

/**
 * <h1>The Interface ITile.</h1>
 *
 */
public interface ITile extends ISquare {
	
	Image getImage();
	TileSolidity getSolidity();

}
