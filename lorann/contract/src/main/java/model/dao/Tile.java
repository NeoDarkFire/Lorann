package model.dao;

public class Tile {

	private int id;
	private int x;
	private int y;
	private int id_map;
	private int id_element;
	
	public Tile(int id, int x, int y, int id_map, int id_element) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.id_map = id_map;
		this.id_element = id_element;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getId_map() {
		return id_map;
	}

	public void setId_map(int id_map) {
		this.id_map = id_map;
	}

	public int getId_element() {
		return id_element;
	}

	public void setId_element(int id_element) {
		this.id_element = id_element;
	}
	
}
