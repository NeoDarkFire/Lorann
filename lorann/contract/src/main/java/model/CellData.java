package model;

public class CellData {
	
//----------------------ATTRIBUTES--------------------	
	private int id;
	private int x;
	private int y;
	private char symbol;
	private int idMap;
	private final static int IDCOL = 1;
	private final static int XCOL = 2;
	private final static int YCOL = 3;
	private final static int SYMBOLCOL = 4;
	private final static int IDMAPCOL = 5;

//----------------------METHODS---------------------	
	public CellData(int id, int x, int y, int idMap, int idGameObject) {}

	public int getID() {
		return id;
	}

	public void setID(int id) {
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

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getIDMap() {
		return idMap;
	}

	public void setIDMap(int idMap) {
		this.idMap = idMap;
	}

	

}
