package model;

public abstract class TileFactory {

	private final static ITile EMPTY_TILE = (ITile) new Tile("empty.png", TileSolidity.FREE);
	private final static ITile BONE = (ITile) new Tile("bone.png", TileSolidity.SOLID);
	private final static ITile VBONE = (ITile) new Tile("vertical_bone.png", TileSolidity.SOLID);
	private final static ITile HBONE = (ITile) new Tile("horizontal_bone.png", TileSolidity.SOLID);

	public static ITile getFromSymbol(char symbol) {
		switch (symbol) {
		case ' ': 	return getEmptyTile();
		case 'O': 	return getBone();
		case 'I': 	return getVerticalBone();
		case '-':	return getHorizontalBone();
		default:	return null;
		}
	}
	
	public static ITile getEmptyTile() {
		return EMPTY_TILE;
	}
	
	public static ITile getBone() {
		return BONE;
	}
	
	public static ITile getVerticalBone() {
		return VBONE;
	}
	
	public static ITile getHorizontalBone() {
		return HBONE;
	}
}
