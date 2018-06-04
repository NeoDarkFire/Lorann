package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD
=======
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
	public ILevel getLevelByID(int id) throws SQLException {
		MapData map = LevelDAO.getMapWithID(id);
		List<CellData> cells = LevelDAO.getCellsByMapID(id);
		LevelData levelData = new LevelData(cells, map);
		return (ILevel) new Level(levelData);
	}
	
	@Override
	public void saveFromFile(File file, int mapID) throws FileNotFoundException, SQLException {
		LevelDAO.saveFromFile(file, mapID);
	}
	
}
