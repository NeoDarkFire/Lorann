package model;

import java.util.ArrayList;

import ecs.Entity;

public interface ILevel {
	
	public int getWidth();
	public int getHeight();
	public ITile getTileAt(int x, int y);
	public void reload();
	public void addEntity(Entity entity);
	public void removeEntity(Entity entity);
	public ArrayList<Entity> getEntities();
	public Entity getEntityAt(int x, int y);
	public int getID();

}
