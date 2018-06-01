package model;

public class MapData {
	
//----------------------ATTRIBUTES--------------------
	private int id;
	private int width;
	private int height;
	private final static int IDCOL = 1 ;
	private final static int WIDTHCOL= 2 ;
	private final static int HEIGHTCOL= 3 ;
	
//----------------------METHODS---------------------	
	public MapData(int id, int width, int height) {}

	public int getID() {
		return id;
	}

	public void setID(int id) {
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
