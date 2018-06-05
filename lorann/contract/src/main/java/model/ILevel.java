package model;

import java.util.List;

import ecs.Entity;

/**
 * <h1>The Interface ILevel.</h1>
 *
 */
public interface ILevel {
	
	int getWidth();
	int getHeight();
	ITile getTileAt(int x, int y);
	void reload();
	void addEntity(Entity entity);
	void removeEntity(Entity entity);
	List<Entity> getEntities();
	int getID();
	Entity getEntityAt(int x, int y);

}
