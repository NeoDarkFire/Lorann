package model;

public abstract class TileFactory {

	private final static ITile EMPTY_TILE = new Tile("empty.png", TileSolidity.FREE);
	private final static ITile BONE = new Tile("bone.png", TileSolidity.SOLID);
	private final static ITile VBONE = new Tile("vertical_bone.png", TileSolidity.SOLID);
	private final static ITile HBONE = new Tile("horizontal_bone.png", TileSolidity.SOLID);

	public static ITile getFromSymbol(char symbol) {
		return null;
	}
	
	public static ITile getEmptyTile() {
		return null;
	}
	
	public static ITile getBone() {
		return null;
	}
	
	public static ITile getVerticalBone() {
		return null;
	}
	
	public static ITile getHorizontalBone() {
		return null;
	}
}
