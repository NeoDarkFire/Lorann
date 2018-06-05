package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ecs.Entity;

import model.components.PositionComponent;

public class Level implements ILevel {

	private ITile[][] grid;
	private List<Entity> entities;
	private LevelData levelData;
	
	public Level(LevelData levelData) {
		this.levelData = levelData;
		this.load();
	}
	
	public int getWidth() {
		return this.levelData.getMap().getWidth();
	}
	
	public int getHeight() {
		return this.levelData.getMap().getHeight();
	}
	
	private void load() {
		List<CellData> cells = this.levelData.getGrid();
		ITile tile = TileFactory.getEmptyTile();
		Entity entity;
		int x, y;
		Point pos;
		
		this.grid = new ITile[this.getHeight()][this.getWidth()];
		for (y = 0; y < this.getHeight(); y++) {
			for (x = 0; x < this.getWidth(); x++) {
				this.grid[y][x] = tile;
			}
		}
		this.entities = null;
		this.entities = new ArrayList<>();
		
		for (final CellData cell : cells) {
			x = cell.getX();
			y = cell.getY();
			tile = TileFactory.getFromSymbol(cell.getSymbol());
			entity = EntityFactory.getFromSymbol(cell.getSymbol());
			
			if (tile != null) {
				this.grid[y][x] = tile;
			}
			else if (entity != null) {
				pos = entity.get(PositionComponent.class).pos;
				pos.x = x;
				pos.y = y;
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
	
	public List<Entity> getEntities(){
		return this.entities;
	}
	
	@Override
	public Entity getEntityAt(int x , int y) {
		PositionComponent pos;
		for (final Entity e : this.entities) {
			pos = e.get(PositionComponent.class);
			if (pos != null && pos.pos.getX() == x && pos.pos.getY() == y) {
				return e;
			}
		}
		return null;
	}
	
	public int getID() {
		return this.levelData.getMap().getID();
	}
}
