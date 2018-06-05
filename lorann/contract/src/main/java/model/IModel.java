package model;

import java.io.File;

import ecs.Entity;

/**
 * <h1>The Interface IModel.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IModel {
    ILevel getLevelByID(int id);
    void saveFromFile(File file, int mapID);
    void saveFromFile(File file);
    Entity createLorann();
    Entity createDemon(int type);
    Entity createSpell();
    Entity createMask();
	Entity createDoor();
	Entity createEnergyBall();
	Entity createPurse();

}
