package model.components;

import model.CollisionType;
import ecs.Component;

/**
 * <h1>Mark if the component is solid or not</h1>
 *
 */
public class SolidComponent implements Component {
	
	/**
	 * the type of the collision
	 */
	public CollisionType type;

}
