package ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Engine {

	private Map<Integer, Entity> entities;
	private Map<Class<? extends Component>, Component> components;
	private Map<Class<? extends System>, System> systems;
	private List<System> orderedSystems;
	private Stack<Integer> unusedIDs;
	private int maxID;
	
	public Engine() {
		this.entities = new HashMap<>();
		this.components = new HashMap<>();
		this.systems = new HashMap<>();
		this.orderedSystems = new ArrayList<>();
		this.unusedIDs = new Stack<>();
		this.maxID = 0;
	}
	
	public void addComponent(final Component comp) {
		this.components.put(comp.getClass(), comp);
	}
	
	public Component getComponent(final Class<? extends Component> compClass) {
		return this.components.get(compClass);
	}
	
	public boolean hasComponent(final Class<? extends Component> compClass) {
		return this.components.containsKey(compClass);
	}
	
	public Collection<Entity> getEntitiesWithComponent(final Class<? extends Component> compClass) {
		Set<Entity> entities = new HashSet<>();
		for (final Entity e : this.entities.values()) {
			if (e.has(compClass)) {
				entities.add(e);
			}
		}
		return entities;
	}
	
	public Collection<Entity> getEntitiesWithComponents(final Collection<Class<? extends Component>> compClasses) {
		Set<Entity> entities = new HashSet<>();
		for (final Entity e : this.entities.values()) {
			if (e.has(compClasses)) {
				entities.add(e);
			}
		}
		return entities;
	}
	
	private void validateComponentFromEntity(final Class<? extends Component> compClass, final Entity e) throws Exception {
		final Object defaultComp = this.getComponent(compClass);
		if (defaultComp == null) {
			throw new Exception(String.format("The component %s is not registered.", compClass.getName()));
		}
	}
	
	private void validateEntity(final Entity e) {
		try {
			for (final Component comp : e.allComponents()) {
				this.validateComponentFromEntity(comp.getClass(), e);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private int getID() {
		if (this.unusedIDs.empty()) {
			this.maxID++;
			return this.maxID;
		}
		else {
			return this.unusedIDs.pop();
		}
	}
	
	public void addEntity(final Entity e) {
		final int id = this.getID();
		e.setID(id);
		this.entities.put(id, e);
		this.validateEntity(e);
		e.register(this);
		for (final System sys : this.orderedSystems) {
			if (e.has(sys.getTargets()) && !e.has(sys.getExcluded())) {
				sys.addEntity(e);
			}
		}
	}
	
	public Entity removeEntity(final int id) {
		final Entity e = this.getEntity(id);
		for (final System sys : this.orderedSystems) {
			if (sys.hasEntity(e)) {
				sys.removeEntity(e);
			}
		}
		e.unregister();
		this.unusedIDs.push(e.getID());
		this.entities.remove(e.getID());
		return e;
	}
	
	public Entity getEntity(final int id) {
		return this.entities.get(id);
	}
	
	public boolean hasSystem(final Class<? extends System> sysClass) {
		return this.systems.containsKey(sysClass);
	}
	
	public void addSystem(final System sys) {
		this.systems.put(sys.getClass(), sys);
		this.orderedSystems.add(sys);
		for (final Entity e : this.getEntitiesWithComponents(sys.getTargets())) {
			if (!e.has(sys.getExcluded())) {
				sys.addEntity(e);
			}
		}
	}
	
	public void removeSystem(final Class<? extends System> sysClass) {
		final System sys = this.systems.get(sysClass);
		this.orderedSystems.remove(sys);
		this.systems.remove(sysClass);
		for (final Entity e : sys) {
			sys.removeEntity(e);
		}
	}
	
	void notifySystems(final Entity e, final EntityAction action) {
		if (action == EntityAction.DESTROY) {
			this.removeEntity(e.getID());
		}
	}
	
	void notifySystems(final Entity e, EntityAction action, Class<? extends Component> compClass) {
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
	
	public void update(int dt) {
		for (final System sys : this.orderedSystems) {
			sys.update(this, dt);
		}
	}

}
