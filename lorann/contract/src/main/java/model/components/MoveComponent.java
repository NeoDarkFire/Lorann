package model.components;

import model.Movement;
import ecs.Component;

/**
 * <h1>Entity is able to move</h1>
 *
 */
public class MoveComponent implements Component {
	
	/**
	 * the movement the entity wants to do
	 */
	public Movement movement;

}
