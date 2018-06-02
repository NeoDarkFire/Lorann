package model;

import java.util.ArrayList;
import java.util.List;

import ecs.Entity;

public class LevelData {

	private List<CellData> grid;
	private MapData map;
	
	public LevelData(List<CellData> grid, MapData map) {
		this.setMap(map);
		this.setGrid(grid);
	}

	public List<CellData> getGrid() {
		return grid;
	}

	public void setGrid(List<CellData> grid) {
		this.grid = grid;
	}

	public MapData getMap() {
		return map;
	}

	public void setMap(MapData map) {
		this.map = map;
	}
}
