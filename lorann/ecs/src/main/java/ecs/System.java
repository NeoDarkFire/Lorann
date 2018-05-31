package ecs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class System {

	static protected Set<Class<? extends Component>> targets;
	static protected Set<Class<? extends Component>> excluded;
	private Set<Entity> entities;
	
	private class EntityComparator implements Comparator<Entity> {
		
		private System system;
		
		public EntityComparator(System system) {
			this.system = system;
		}
		
		@Override
		public int compare(Entity e0, Entity e1) {
			return system.priority(e0) - system.priority(e1);
		}
		
	}

	public System() {
		this.entities = new TreeSet<>(new EntityComparator(this));
	}
	
	boolean requires(final Class<? extends Component> compClass) {
		return targets.contains(compClass);
	}
	
	void addEntity(final Entity e) {
		if (!this.hasEntity(e)) {
			this.getEntities().add(e);
			this.onEntityAdded(e);
		}
	}
	
	void removeEntity(final Entity e) {
		if (this.hasEntity(e)) {
			this.getEntities().remove(e);
			this.onEntityRemoved(e);
		}
	}
	
	public Set<Entity> getEntities() {
		return this.entities;
	}
	
	public Iterator<Entity> getIterator() {
		return this.getEntities().iterator();
	}
	
	boolean hasEntity(Entity e) {
		return this.getEntities().contains(e);
	}
	
	public int priority(Entity e) {
		return e.getID();
	}
	
	public void onStart() {
	}
	
	public void onStop() {	
	}
	
	public void onEntityAdded(Entity e) {
	}
	
	public void onEntityRemoved(Entity e) {
	}
	
	public void update(Engine engine, int dt) {	
	}
	
}
