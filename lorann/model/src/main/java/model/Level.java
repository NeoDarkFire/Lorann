package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ecs.Entity;

import model.components.PositionComponent;

public class Level {

	private ITile[][] grid;
	private int width;
	private int height ;
	private ArrayList<Entity> entities;
	private LevelData levelData;
	
	public Level(LevelData levelData) {
		this.levelData = levelData;
		this.load();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() {
		List<CellData> cells = this.levelData.getGrid();
		ITile tile;
		Entity entity;
		int x, y;
		Point pos;
		
		for (final CellData cell : cells) {
			x = cell.getX();
			y = cell.getY();
			tile = TileFactory.getFromSymbol(cell.getSymbol());
			entity = EntityFactory.getFromSymbol(cell.getSymbol());
			
			if (tile != null) {
				this.grid[y][x] = tile;
			}
			else if (entity != null) {
				pos = ((PositionComponent) entity.get(PositionComponent.class)).pos;
				pos.move(x, y);
				this.addEntity(entity);
			}
		}
	}
	
	public ITile getTileAt(int x , int y) {
		return grid[y][x];
	}
	
	public void reload() {
		this.load();
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public ArrayList<Entity> getEntities(){
		return this.entities;
	}
	
	public Entity getEntityAt(int x , int y) {
		Point pos;
		for (final Entity e : this.entities) {
			pos = ((PositionComponent) e.get(PositionComponent.class)).pos;
			if (pos.getX() == x && pos.getY() == y) {
				return e;
			}
		}
		return null;
	}
	
	public int getID() {
		return this.levelData.getMap().getID();
	}
}
