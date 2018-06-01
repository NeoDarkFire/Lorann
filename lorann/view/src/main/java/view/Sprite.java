package view;

import java.awt.Image;
import java.awt.Point;

import showboard.IPawn;
import model.components.*;

/**
 * <h1>The Class Sprite uses the "showboard" to recover differents information about the position and the image.</h1>
 *
 * 
 * @version 1.0
 */

public class Sprite implements IPawn {
	
	private Entity entity;
	
	
	public Sprite(Entity entity) {
		this.entity = entity;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.entity.get(DrawableComponent.class).image;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.entity.get(PositionComponent.class).x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.entity.get(PositionComponent.class).y;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return new Point(this.getX(),this.getY());
	}
	
	
	
	
	

}
