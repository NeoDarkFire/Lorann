package ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Engine {

	private ArrayList<Entity> entities;
	private HashMap<Class<? extends Component>, Component> components;
	private HashMap<Class<? extends System>, System> systems;
	private ArrayList<System> runningSystems;
	private List<Integer> unusedIDs;
	private int maxID;
	
	public Engine() {
		// TODO Auto-generated constructor stub
	}
	
	public void addComponent(Component comp) {
		
	}
	
	public Component getComponent(Class<? extends Component> compClass) {
		return null;
	}
	
	public boolean hasComponent(Class<? extends Component> compClass) {
		return false;
	}
	
	public ArrayList<Entity> getEntitiesWithComponent(Class<? extends Component> compClass) {
		return null;
	}
	
	public ArrayList<Entity> getEntitiesWithComponents(ArrayList<Class<? extends Component>> compClasses) {
		return null;
	}
	
	private void validateComponentFromEntity(Class<? extends Component> compClass, Entity e) {
		
	}
	
	private void validateEntity(Entity e) {
		
	}
	
	private int getID() {
		return 0;
	}
	
	public void addEntity(Entity e) {
		
	}
	
	public Entity removeEntity(int id, boolean removeChildren) {
		return null;
	}
	
	public Entity removeEntity(int id) {
		return null;
	}
	
	public Entity getEntity(int id) {
		return null;
	}
	
	public boolean hasSystem(Class<? extends System> sysClass) {
		return false;
	}
	
	public void addSystem(System sys) {
		
	}
	
	public void removeSystem(Class<? extends System> sysClass) {
		
	}
	
	public void startSystem(Class<? extends System> sysClass) {
		
	}
	
	public void stopSystem(Class<? extends System> sysClass) {
		
	}
	
	public void toggleSystem(Class<? extends System> sysClass) {
		
	}
	
	public boolean isSystemRunning(Class<? extends System> sysClass) {
		return false;
	}
	
	void notifySystems(Entity e, EntityAction action) {
		
	}
	
	void notifySystems(Entity e, EntityAction action, Class<? extends Component> compClass) {
		
	}
	
	public void update(int dt) {
		
	}

}
