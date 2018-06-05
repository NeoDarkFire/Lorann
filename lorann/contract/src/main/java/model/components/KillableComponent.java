package model.components;

import ecs.Component;

/**
 * <h1>the entity  has been killable</h1>
 */
public class KillableComponent implements Component {
	
	/**
	 * component to wich the entity can die
	 */
	public Class<? extends Component> weakness;

}
