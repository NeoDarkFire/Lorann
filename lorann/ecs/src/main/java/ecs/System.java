package ecs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * The System class is basically an algorithm designed for a Set of Entity matching some Component criteria.
 * 
 * @author Alexis SKRZYNSKI (alexis.skrzynski@viacesi.fr) aka NeoDarkFire
 * 
 * @see ecs.Component
 * @see ecs.Entity
 *
 */
public abstract class System implements Iterable<Entity> {

	/**
	 * Which components the System targets.
	 */
	protected Set<Class<? extends Component>> targets;
	/**
	 * Which components are excluded by the System.
	 */
	protected Set<Class<? extends Component>> excluded;
	
	/**
	 * The entities that conform to the System's criteria.
	 */
	private Set<Entity> entities;
	
	/**
	 * Intern Class for keeping the System's entities sorted by the priority method.
	 * It compares two entities using the aforementioned method.
	 * @see priority
	 */
	private class EntityComparator implements Comparator<Entity> {
		
		/**
		 * The System comparing the entities.
		 */
		private System system;
		
		public EntityComparator(final System system) {
			this.system = system;
		}
		
		/**
		 * Compare two entities using the System's priority method.
		 */
		@Override
		public int compare(final Entity e0, final Entity e1) {
			return system.priority(e0) - system.priority(e1);
		}
		
	}

	public System() {
		this.entities = new TreeSet<>(new EntityComparator(this));
		this.targets = new HashSet<>();
		this.excluded = new HashSet<>();
	}
	
	/**
	 * Checks if a Component is required by the System.
	 * @param compClass The class of the component.
	 * @return True if the Component is required.
	 */
	boolean requires(final Class<? extends Component> compClass) {
		return targets.contains(compClass);
	}
	
	/**
	 * <p>
	 * Adds an entity to the System.
	 * An Entity cannot be added twice.
	 * <p>
	 * Note: This does check if the Entity is conform.
	 * @param e An Entity.
	 */
	void addEntity(final Entity e) {
		if (!this.hasEntity(e) && e.hasAll(this.getTargets()) && !e.hasOne(this.getExcluded())) {
			this.getEntities().add(e);
			this.onEntityAdded(e);
		}
	}
	
	/**
	 * Removes an entity from the System.
	 * @param e An Entity.
	 */
	void removeEntity(final Entity e) {
		if (this.hasEntity(e)) {
			this.getEntities().remove(e);
			this.onEntityRemoved(e);
		}
	}
	
	/**
	 * Gets all the entities of the System.
	 * @return A Set of Entity.
	 */
	public Set<Entity> getEntities() {
		return this.entities;
	}
	
	/**
	 * <p>Allows the System to be used in a for-each statement.
	 * <p>Example: {@code for (final Entity e : system) {}}
	 */
	@Override
	public Iterator<Entity> iterator() {
		return this.getEntities().iterator();
	}
	
	/**
	 * Checks if the System has an Entity.
	 * @param e An Entity.
	 * @return True is the System contains the Entity.
	 */
	boolean hasEntity(final Entity e) {
		return this.getEntities().contains(e);
	}
	
	/**
	 * <p>This method allows to compare Entities to process them in a specified order.
	 * <p>The higher the return value, the later the Entity is to be processed.
	 * @param e An Entity.
	 * @return A number.
	 */
	public int priority(final Entity e) {
		return e.getID();
	}
	
	/**
	 * Callback that gets called each time an Entity gets added to the System.
	 * @param e An Entity.
	 */
	public void onEntityAdded(final Entity e) {
	}
	
	/**
	 * Callback that gets called each time an Entity gets removed from the System.
	 * @param e An Entity.
	 */
	public void onEntityRemoved(final Entity e) {
	}
	
	/**
	 * Process the System. Typically uses itself in a for-each.
	 * @see iterator
	 * @param engine An Engine
	 * @param dt The delta time, the amount of time elapsed since the last update.
	 */
	public void update(final Engine engine, final int dt) {
	}

	/**
	 * Gets all the components which the systems targets.
	 * @return A Set of Component Class.
	 */
	public Set<Class<? extends Component>> getTargets() {
		return this.targets;
	}
	
	/**
	 * Gets all the components excluded by the systems.
	 * @return A Set of Component Class.
	 */
	public Set<Class<? extends Component>> getExcluded() {
		return this.excluded;
	}
	
}
