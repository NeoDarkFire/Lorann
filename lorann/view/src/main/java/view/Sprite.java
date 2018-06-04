package view;

import java.awt.Image;
import java.awt.Point;

import ecs.Entity;
import showboard.IPawn;
import model.components.*;

/**
 * <h1>The Class Sprite uses the "showboard" to recover different informations about the position and the image.</h1>
 *
 * @author aurel
 * @version 1.0
 */

public class Sprite implements IPawn {
	
	private Entity entity;
	
	/**
	 * Configures the sprite
	 * @param entity
	 */
	public Sprite(Entity entity) {
		this.entity = entity;
	}
	/**
	 * Makes the image drawable
	 * 
	 */
	@Override
	public Image getImage() {
		final DrawableComponent comp = this.entity.get(DrawableComponent.class);
		return comp.image;
	}

	/**
	 * Get the position X
	 * 
	 */
	@Override
	public int getX() {
		final PositionComponent comp = this.entity.get(PositionComponent.class);
		return (int) comp.pos.getX();
	}
	/**
	 * Get the position Y
	 */

	@Override
	public int getY() {
		final PositionComponent comp = this.entity.get(PositionComponent.class);
		return (int) comp.pos.getY();
	}

	/**
	 * Get the position X and Y
	 */
	@Override
	public Point getPosition() {
		return new Point(this.getX(),this.getY());
	}
	
}
