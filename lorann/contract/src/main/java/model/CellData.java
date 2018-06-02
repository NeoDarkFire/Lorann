package model;

public class CellData {
	
//----------------------ATTRIBUTES--------------------	
	private int id;
	private int x;
	private int y;
	private char symbol;
	private int idMap;
	public final static int IDCOL = 1;
	public final static int XCOL = 2;
	public final static int YCOL = 3;
	public final static int SYMBOLCOL = 4;
	public final static int IDMAPCOL = 5;

//----------------------METHODS---------------------	
	public CellData(int id, int x, int y, char symbol, int idMap) {
		this.setID(id);
		this.setX(x);
		this.setY(y);
		this.setSymbol(symbol);
		this.setIDMap(idMap);
	}

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
