package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import ecs.Entity;
import model.dao.LevelDAO;


/**
 * <h1>The Class ModelFacade provides a facade of the Model component.</h1>
 *
 * @author Alexis SKRZYNSKI (alexis.skrzynski@viacesi.fr) aka NeoDarkFire
 * @version 1.0
 */
public final class ModelFacade implements IModel {

    /**
     * Instantiates a new model facade.
     */
    public ModelFacade() {
        super();
    }


	@Override
	public ILevel getLevelByID(int id) {
		MapData map = null;
		try {
			map = LevelDAO.getMapWithID(id);
			if (map == null) {
				return null;
			}
			List<CellData> cells = LevelDAO.getCellsByMapID(id);
			if (cells.size() > 0) {
				LevelData levelData = new LevelData(cells, map);
				return (ILevel) new Level(levelData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void saveFromFile(File file, int mapID) {
		try {
			LevelDAO.saveFromFile(file, mapID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void saveFromFile(File file) {
		try {
			LevelDAO.saveFromFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Entity createSpell() {
		return EntityFactory.createSpell();
	}
	
	@Override
	public Entity createDoor() {
		return EntityFactory.createDoor();
	}
	
}
