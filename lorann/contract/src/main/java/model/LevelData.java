package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelData {

	private List<CellData> grid;
	private MapData map;
	
	public LevelData(List<CellData> grid, MapData map) {
		this.setMap(map);
		this.setGrid(grid);
	}
	
	public LevelData(File file) throws FileNotFoundException {
		List<CellData> cells = new ArrayList<>();
		final Scanner sc = new Scanner(file);
		int map_width = 0;
		int map_height = 0;
		int x = 0;
		int y = 0;
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			for (x = 0; x < line.length(); x++) {
				final char symbol = line.charAt(x);
				cells.add(new CellData(0, x, y, symbol, 0));
			}
			map_width = Math.max(map_width, x);
			y++;
		}
		map_height = y;
		sc.close();
		this.setGrid(cells);
		this.setMap(new MapData(0, map_width, map_height));
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
