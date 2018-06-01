package model;

import java.util.ArrayList;

import ecs.Entity;

public class Level {

	private ITile[][] grid;
	private int width;
	private int height ;
	private ArrayList<Entity> entities;
	private LevelData levelData;
	
	public Level(LevelData levelData) {
		this.levelData = levelData;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() {
		
	}
	
	public ITile getTileAt(int x , int y) {
		return null;
	}
	
	public void reload() {
		
	}
	
	public void addEntity(Entity entity) {
		
	}
	
	public void removeEntity(Entity entity) {
		
	}
	
	public ArrayList<Entity> getEntities(){
		return null;
	}
	
	public Entity getEntityAt(int x , int y) {
		return null;
	}
	
	public int getID() {
		return 0;
	}
}
