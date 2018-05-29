package model.dao;

public class Map {

	private int id;
	private int width;
	private int height;
	private final static int WIDTH = 20;
	private final static int HEIGHT = 12;
	
	
	public Map(int id, int width, int height) {
		this.id = id;
		this.width = WIDTH;
		this.height= HEIGHT;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
