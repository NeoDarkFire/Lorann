package model.dao;

import java.io.File;
import java.util.ArrayList;

import ecs.Entity;
import model.ITile;
import model.MapData;

public abstract class LevelDAO {

	private String sqlTilesByMapID = "call findTilesByMapID";
	private String sqlMapWithID = "call findMapWithID(?)";
	private String sqlEntitiesByMapID = "call findEntitiesByMapID";

	public MapData getMapWithID(int id) {
		
		return null;
	}
	
	public ArrayList<ITile> getTilesByMapID(int id){
		
		return null;
	}
	
	public ArrayList<Entity> getEntitiesByMapID(int id){
		
		return null;
	}
	
	public void saveFromFile (File file) {
		
	}
}
