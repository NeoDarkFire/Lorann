package model.components;

import ecs.Component;
import model.Direction;
import model.Movement;

/**
 * <h1>Mark the entity has been able to cast a spell</h1>
 *
 */
public class SpellCasterComponent implements Component {

	/**
	 * movement the spell gonna have when throw
	 */
	public Movement spellMovement;

}
