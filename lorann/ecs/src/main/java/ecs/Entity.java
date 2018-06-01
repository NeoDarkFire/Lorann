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
	 * Creates an Entity with one specified Component.
	 */
	
	public Entity(final Component comp) {
		this();
		this.attach(comp);
	}
	
	/**
	 * Creates an Entity with a Collection of Component.
	 */
	
	public Entity(final Collection<Component> comps) {
		this();
		for (final Component comp : comps) {
			this.attach(comp);
		}
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
	 * Gets the Component attached to the Entity.
	 * @param compClass The class of the Component.
	 * @return The Component.
	 */
	public Component get(final Class<? extends Component> compClass) {
		return this.components.get(compClass);
	}
	
	/**
	 * Updates the Component associated without notifying the Engine.
	 * A Component must already be registered with the Entity.
	 * @param compClass The class of the Component.
	 * @param comp The Component.
	 * @return The old Component.
	 */
	public Component set(final Class<? extends Component> compClass, final Component comp) {
		return this.components.replace(compClass, comp);
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
		return this.components.containsKey(compClass);
	}
	
	/**
	 * Get whether if the Entity has multiple specified Components.
	 * @param compClasses A Collection of Component classes.
	 * @return True if the Entity has all the Components.
	 */
	public boolean has(final Collection<Class<? extends Component>> compClasses) {
		for (Class<? extends Component> compClass : compClasses) {
			if (!this.has(compClass)) {
				return false;
			}
		}
		return true;
	}

}