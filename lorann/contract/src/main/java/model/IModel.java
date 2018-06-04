package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import ecs.Entity;

/**
 * <h1>The Interface IModel.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IModel {
    public ILevel getLevelByID(int id);
    public void saveFromFile(File file, int mapID);
    public void saveFromFile(File file);
	public Entity createSpell();
	public Entity createDoor();

}
