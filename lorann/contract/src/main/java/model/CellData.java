package model;

/**
 * <h1>Used to fetch the data about the cells from the database </h1>
 *
 */
public class CellData {
	
//----------------------ATTRIBUTES--------------------	
	private int id;
	private int x;
	private int y;
	private char symbol;
	private int mapID;
	/**
	 * determine the number of the column corresponding to the ID in the database
	 */
	public final static int IDCOL = 1;
	/**
	 * determine the number of the column corresponding to the x in the database
	 */
	public final static int XCOL = 2;
	/**
	 * determine the number of the column corresponding to the Y in the database
	 */
	public final static int YCOL = 3;
	/**
	 * determine the number of the column corresponding to the SYMBOL in the database
	 */
	public final static int SYMBOLCOL = 4;
	/**
	 * determine the number of the column corresponding to the IDMAP in the database 
	 */
	public final static int IDMAPCOL = 5;

//----------------------METHODS---------------------
	/**
	 * constructor
	 * @param id The ID of the cell.
	 * @param x The X position of the cell.
	 * @param y The Y position of the cell.
	 * @param symbol The symbol of the cell.
	 * @param mapID The ID of the map containing the cell.
	 */
	public CellData(int id, int x, int y, char symbol, int mapID) {
		this.setID(id);
		this.setX(x);
		this.setY(y);
		this.setSymbol(symbol);
		this.setMapID(mapID);
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

	public int getMapID() {
		return mapID;
	}

	public void setMapID(int mapID) {
		this.mapID = mapID;
	}

	

}
