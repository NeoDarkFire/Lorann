package ecs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * The Entity class describes an Entity used by Engine and System.
 * An Entity is like a powerful bag of as many Component as you want.
 * 
 * @author Alexis SKRZYNSKI (alexis.skrzynski@viacesi.fr) aka NeoDarkFire
 * 
 * @see ecs.Component
 * @see ecs.Engine
 * @see ecs.System 
 *
 */
public class Entity {

	/**
	 * The id is used to identify an Entity in an Engine.
	 * It is not managed manually.
	 */
	private int id;
	
	/**
	 * An entity is associated with one and only one Engine.
	 */
	private Engine engine;
	
	/**
	 * An entity associates a Component with it's class, thus allowing only one Component of the same type.
	 */
	private Map<Class<? extends Component>, Component> components;
	
	
	/**
	 * Default constructor. Creates an empty Entity, without any Component.
	 */
	public Entity() {
		this.components = new HashMap<>();
	}
	
	/**
	 * Creates an Entity with one to many Components.
<<<<<<< HEAD
	 * @param comps Components.
=======
>>>>>>> branch 'ecs' of https://github.com/NeoDarkFire/Lorann.git
	 */
	
	public Entity(final Component... comps) {
		this();
		this.attach(comps);
	}
	
	/**
	 * Creates an Entity with a Collection of Components.
<<<<<<< HEAD
	 * @param comps A Collection of Component.
=======
>>>>>>> branch 'ecs' of https://github.com/NeoDarkFire/Lorann.git
	 */
	
	public Entity(final Collection<Component> comps) {
		this();
		this.attach(comps);
	}
	
	/**
	 * Example : "Entity #1"
	 */
	@Override
	public String toString() {
		return String.format("Entity #%d", this.getID());
	}
	
	/**
	 * Sets the ID of the Entity.
	 * Only package members may use it (typically Engine).
	 * 
	 * @param id A unique number.
	 */
	void setID(final int id) {
		this.id = id;
	}
	
	/**
	 * Gets the unique ID identifying the Entity.
	 * @return The ID of the Entity. 
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Destroys the Entity.
	 * If the Entity has components, destroy them.
	 * If the Entity is registered to an Engine, tells it to discard the Entity. 
	 */
	public void destroy() {
		if (this.engine != null) {
			this.engine.getNotification(this, EntityAction.DESTROY);
		}
		for (final Class<? extends Component> compClass : this.components.keySet()) {
			this.detach(compClass);
		}
	}
	
	/**
	 * Associates the Entity with an Engine.
	 * @param engine The Engine.
	 */
	void register(final Engine engine) {
		this.engine = engine;
	}
	
	/**
	 * Unregister the Entity from its Engine.
	 */
	void unregister() {
		this.engine = null;
	}
	
	/**
	 * Attach a Component to the Entity.
	 * If there is already a Component for that Component class, detach it.
	 * @param comp A Component.
	 * @return The old Component, when applicable.
	 * @see detach
	 */
	public Component attach(final Component comp) {
		final Class<? extends Component> compClass = comp.getClass();
		Component oldComp = null;
		if (this.has(compClass)) {
			oldComp = this.detach(compClass);
		}
		this.components.put(compClass, comp);
		if (this.engine != null) {
			this.engine.notifySystems(this, EntityAction.ATTACH, compClass);
		}
		return oldComp;
	}
	
	/**
	 * Attach one to many Component to the Entity.
	 * If there is already a Component for a Component class, detach it.
	 * @param comps Components.
	 * @see detach
	 */
	public void attach(final Component... comps) {
		for (final Component comp : comps) {
			this.attach(comp);
		}
	}
	
	/**
	 * Attach a Collection of Component to the Entity.
	 * If there is already a Component for a Component class, detach it.
	 * @param comps A Collection of Component.
	 * @see detach
	 */
	public void attach(final Collection<Component> comps) {
		for (final Component comp : comps) {
			this.attach(comp);
		}
	}
	
	/**
	 * Detaches a Component from the Entity.
	 * @param compClass The class of the Component to detach.
	 * @return the old Component.
	 */
	public Component detach(final Class<? extends Component> compClass) {
		Component oldComp = this.components.remove(compClass);
		if (this.engine != null && oldComp != null) {
			this.engine.notifySystems(this, EntityAction.DETACH, compClass);
		}
		return oldComp;
	}
	
	/**
	 * Detaches one to many Component from the Entity.
	 * @param compClasses The classes of the Components to detach.
	 */
	@SafeVarargs
	final public <C extends Component> void detach(final Class<C>... compClasses) {
		for (final Class<C> compClass : compClasses) {
			this.detach(compClass);
		}
	}
	
	/**
	 * Detaches a Collection of Component from the Entity.
	 * @param compClasses A Collection of the class of the Components to detach.
	 */
	public <C extends Component> void detach(final Collection<Class<C>> compClasses) {
		for (final Class<C> compClass : compClasses) {
			this.detach(compClass);
		}
	}
	
	/**
	 * Gets the Component attached to the Entity.
	 * @param compClass The class of the Component.
	 * @return The Component.
	 */
	@SuppressWarnings("unchecked")
	public <C extends Component> C get(final Class<C> compClass) {
		return (C) this.components.get(compClass);
	}
	
	/**
	 * Get all the Components of the Entity.
	 * @return A Collection of Component.
	 */
	public Collection<? extends Component> allComponents() {
		return this.components.values();
	}
	
	/**
	 * Get whether if the Entity has a specified Component.
	 * @param compClass The class of the Component.
	 * @return True if the Entity has the Component.
	 */
	public boolean has(final Class<? extends Component> compClass) {
		return this.components.containsKey(compClass)
			|| (compClass == Component.class && this.components.size() > 0);
	}
	
	/**
	 * Get whether if the Entity has multiple specified Components.
	 * @param compClasses A Collection of Component classes.
	 * @return True if the Entity has all the Components.
	 */
	public boolean hasAll(final Collection<Class<? extends Component>> compClasses) {
		for (Class<? extends Component> compClass : compClasses) {
			if (!this.has(compClass)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get whether if the Entity has multiple specified Components.
	 * @param compClasses Component classes.
	 * @return True if the Entity has all the Components.
	 */
	@SafeVarargs
	final public <C extends Component> boolean hasAll(final Class<C>... compClasses) {
		for (Class<C> compClass : compClasses) {
			if (!this.has(compClass)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Get whether if the Entity has one out of many specified Components.
	 * @param compClasses A Collection of Component classes.
	 * @return True if the Entity has one of the Components.
	 */
	public boolean hasOne(final Collection<Class<? extends Component>> compClasses) {
		for (Class<? extends Component> compClass : compClasses) {
			if (this.has(compClass)) {
				return true;
			}
		}
		return false;
	}

}
