package model;

import java.awt.Image;

import showboard.ISquare;

public interface ITile extends ISquare {
	
	public Image getImage();
	public TileSolidity getSolidity();
	

}
