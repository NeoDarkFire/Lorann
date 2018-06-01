package view;

import java.awt.Image;
import java.awt.Point;

import ecs.Entity;
import showboard.IPawn;
import model.components.*;

/**
 * <h1>The Class Sprite uses the "showboard" to recover different informations about the position and the image.</h1>
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
		final DrawableComponent comp = (DrawableComponent) this.entity.get(DrawableComponent.class);
		return comp.image;
	}

	@Override
	public int getX() {
		final PositionComponent comp = (PositionComponent) this.entity.get(PositionComponent.class);
		return (int) comp.pos.getX();
	}

	@Override
	public int getY() {
		final PositionComponent comp = (PositionComponent) this.entity.get(PositionComponent.class);
		return (int) comp.pos.getY();
	}

	@Override
	public Point getPosition() {
		return new Point(this.getX(),this.getY());
	}
	
}
