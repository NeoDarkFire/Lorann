package model;

import java.awt.Image;

import showboard.ISquare;

public interface ITile extends ISquare {
	
	Image getImage();
	TileSolidity getSolidity();

}
