package model.dao;

import java.util.ArrayList;

public abstract class LevelDAO {

	private String sqlTilesByMapID = "call findTilesByMapID";
	private String sqlMapWithID = "call findMapWithID(?)";
	private String sqlEntitiesByMapID = "call findEntitiesByMapID";

	public MapData getMapWithID(int id) {
		
		return null;
	}
	
	public ArrayList<Itile> getTilesByMapID(int id){
		
		return null;
	}
	
	public ArrayList<Entity> getEntitiesByMapID(int id){
		
		return null;
	}
	
	public void saveFromFile (File file) {
		
	}
}
