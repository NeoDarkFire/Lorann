package model.components;

import ecs.Component;
import ecs.Entity;

/**
 * <h1>The Entity has having a Spell AI</h1>
 *
 */
public class SpellAIComponent implements Component {
	
	/**
	 * tells whose spell it is
	 */
	public Entity owner;

}
