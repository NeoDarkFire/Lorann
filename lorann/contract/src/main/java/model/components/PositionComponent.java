package model.components;

import java.awt.Point;

import ecs.Component;

/**
 * <h1>Mark the entity has having a position</h1>
 *
 */
public class PositionComponent implements Component {
	
	/**
	 * coordinate of the entity
	 */
	public Point pos;

}
