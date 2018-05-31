package ecs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entity {

	private int id;
	private Engine engine;
	private Map<Class<? extends Component>, Component> components;
	
	public Entity() {
		this.components = new HashMap<>();
	}
	
	public Entity(final Component comp) {
		this();
		this.attach(comp);
	}
	
	public Entity(final Collection<Component> comps) {
		this();
		for (final Component comp : comps) {
			this.attach(comp);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Entity #%d", this.getID());
	}
	
	void setID(final int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void destroy() {
		if (this.engine != null) {
			this.engine.notifySystems(this, EntityAction.DESTROY);
		}
	}
	
	void register(final Engine engine) {
		this.engine = engine;
	}
	
	void unregister() {
		this.engine = null;
	}
	
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
	
	public Component detach(final Class<? extends Component> compClass) {
		Component oldComp = this.components.remove(compClass);
		if (this.engine != null && oldComp != null) {
			this.engine.notifySystems(this, EntityAction.DETACH, compClass);
		}
		return oldComp;
	}
	
	public Component get(final Class<? extends Component> compClass) {
		return this.components.get(compClass);
	}
	
	public Component set(final Class<? extends Component> compClass, final Component comp) {
		return this.components.replace(compClass, comp);
	}
	
	public Collection<? extends Component> allComponents() {
		return this.components.values();
	}
	
	public boolean has(final Class<? extends Component> compClass) {
		return this.components.containsKey(compClass);
	}
	
	public boolean has(final Collection<Class<? extends Component>> compClasses) {
		for (Class<? extends Component> compClass : compClasses) {
			if (!this.has(compClass)) {
				return false;
			}
		}
		return true;
	}

}
