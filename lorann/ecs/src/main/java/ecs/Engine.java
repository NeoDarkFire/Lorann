package ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * The Engine class stores Systems and Entities so they can work together with components.
 * 
 * @author Alexis SKRZYNSKI (alexis.skrzynski@viacesi.fr) aka NeoDarkFire
 * 
 * @see ecs.Component
 * @see ecs.Entity
 * @see ecs.System
 *
 */
public final class Engine {

	public boolean _debug = false;
	
	private Map<Integer, Entity> entities;
	private Map<Class<? extends System>, System> systems;
	private List<System> orderedSystems;
	private Stack<Integer> unusedIDs;
	private int maxID;
	
	public Engine() {
		this.entities = new HashMap<>();
		this.systems = new HashMap<>();
		this.orderedSystems = new ArrayList<>();
		this.unusedIDs = new Stack<>();
		this.maxID = 0;
	}
	
	/**
	 * Gets all the entities having a given Component.
	 * @param compClass The Component class.
	 * @return A Collection of Entity.
	 */
	final public Collection<Entity> getEntitiesWithComponent(final Class<? extends Component> compClass) {
		Set<Entity> entities = new HashSet<>();
		for (final Entity e : this.entities.values()) {
			if (e.has(compClass)) {
				entities.add(e);
			}
		}
		return entities;
	}
	
	/**
	 * Gets all the entities having all the given Component.
	 * @param compClasses A Collection of Component class.
	 * @return A Collection of Entity.
	 */
	final public Collection<Entity> getEntitiesWithComponents(final Collection<Class<? extends Component>> compClasses) {
		Set<Entity> entities = new HashSet<>();
		for (final Entity e : this.entities.values()) {
			if (e.hasAll(compClasses)) {
				entities.add(e);
			}
		}
		return entities;
	}
	
	/**
	 * Gets all the entities having all the given Component.
	 * @param compClasses One to many Component class.
	 * @return A Collection of Entity.
	 */
	@SafeVarargs
	final public Collection<Entity> getEntitiesWithComponents(final Class<? extends Component>... compClasses) {
		Set<Entity> entities = new HashSet<>();
		for (final Entity e : this.entities.values()) {
			if (e.hasAll(compClasses)) {
				entities.add(e);
			}
		}
		return entities;
	}
	
	/**
	 * Get a valid ID for a new Entity.
	 * @return A unique ID.
	 */
	final private int getID() {
		if (this.unusedIDs.empty()) {
			this.maxID++;
			return this.maxID;
		}
		else {
			return this.unusedIDs.pop();
		}
	}
	
	/**
	 * <p>Adds an Entity to the Engine.
	 * <p>The Entity is automatically added to the relevant systems.
	 * @param e An Entity.
	 */
	final public void addEntity(final Entity e) {
		final int id = this.getID();
		e.setID(id);
		this.entities.put(id, e);
		e.register(this);
		if (_debug) java.lang.System.out.println("[Engine] Adding entity: " + e + " -> "+ e.allComponents());
		for (final System sys : this.orderedSystems) {
			sys.addEntity(e);
		}
	}
	
	/**
	 * <p>Removes an Entity from the Engine.
	 * <p>The Entity is automatically removed from the relevant systems.
	 * @param id The ID of an Entity in the Engine.
	 */
	final public Entity removeEntity(final int id) {
		final Entity e = this.getEntity(id);
		if (e != null) {
			if (_debug) java.lang.System.out.println("[Engine] Removing entity: " + e + " -> "+ e.allComponents());
			e.unregister();
			for (final System sys : this.orderedSystems) {
				if (sys.hasEntity(e)) {
					sys.removeEntity(e);
				}
			}
			this.unusedIDs.push(e.getID());
			this.entities.remove(e.getID());
		}
		return e;
	}
	
	/**
	 * Gets an Entity of the Engine provided its ID. 
	 * @param id The unique ID of the Entity.
	 * @return An Entity.
	 */
	final public Entity getEntity(final int id) {
		return this.entities.get(id);
	}
	
	/**
	 * Checks if an Entity is in the Engine. 
	 * @param entity The Entity.
	 * @return An Entity.
	 */
	final public boolean hasEntity(final Entity entity) {
		return this.entities.containsValue(entity);
	}
	
	/**
	 * Gets all the entities of the Engine.
	 * @return A Collection of Entity.
	 */
	public Collection<Entity> getEntitities() {
		return this.entities.values();
	}
	
	/**
	 * Checks if a System is in the Engine.
	 * @param sysClass A System class.
	 * @return True if the System is in the Engine.
	 */
	final public boolean hasSystem(final Class<? extends System> sysClass) {
		return this.systems.containsKey(sysClass);
	}
	
	/**
	 * Gets a System in the Engine.
	 * @param sysClass A System class.
	 * @return The System.
	 */
	final public System getSystem(final Class<? extends System> sysClass) {
		return this.systems.get(sysClass);
	}
	
	/**
	 * <p>Adds a System to the Engine.
	 * <p>Relevant entities are automatically added to the System.
	 * @param sys A System.
	 */
	final public void addSystem(final System sys) {
		this.systems.put(sys.getClass(), sys);
		this.orderedSystems.add(sys);
		for (final Entity e : this.getEntitiesWithComponents(sys.getTargets())) {
			sys.addEntity(e);
		}
	}
	
	/**
	 * <p>Removes a System from the Engine.
	 * <p>Relevant entities are automatically removed from the System.
	 * @param sysClass The System Class.
	 */
	final public void removeSystem(final Class<? extends System> sysClass) {
		final System sys = this.systems.get(sysClass);
		this.orderedSystems.remove(sys);
		this.systems.remove(sysClass);
		for (final Entity e : sys) {
			sys.removeEntity(e);
		}
	}
	
	/**
	 * Receive a notification concerning an Action taken on an Entity.
	 * @param e An Entity.
	 * @param action An Action.
	 */
	final void getNotification(final Entity e, final EntityAction action) {
		if (action == EntityAction.DESTROY) {
			this.removeEntity(e.getID());
		}
	}
	
	/**
	 * Forwards a notification concerning an Action taken on an Entity on one of its Component.
	 * @param e An Entity.
	 * @param action An Action.
	 * @param compClass A Component Class.
	 */
	final void notifySystems(final Entity e, EntityAction action, Class<? extends Component> compClass) {
		for (final System sys : this.orderedSystems) {
			if (sys.requires(compClass)) {
				if (sys.hasEntity(e) && action == EntityAction.DETACH) {
					sys.removeEntity(e);
				}
				else if (action == EntityAction.ATTACH) {
					sys.addEntity(e);
				} 
			}
		}
	}
	
	/**
	 * Processes all Systems
	 * @param dt Delta-time, the amount of time elapsed since last update.
	 */
	final public void update(final double dt) {
		for (final System sys : this.orderedSystems) {
			sys.update(this, dt);
		}
	}

}
