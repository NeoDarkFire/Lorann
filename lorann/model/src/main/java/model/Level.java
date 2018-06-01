package model;

import java.util.ArrayList;

public class Level {

	private ITile[][] grid;
	private int width;
	private int height ;
	private ArrayList<Entity> entities;
	
	public Level(LevelData levelData) {
		
	}
	
	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
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
