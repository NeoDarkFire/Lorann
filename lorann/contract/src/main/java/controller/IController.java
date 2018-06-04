package controller;

import model.ILevel;
import model.IModel;
import view.IView;

/**
 * <h1>The Interface IController.</h1>
 * 
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IController {
	
	public void start();
	public IView getView();
	public IModel getModel();
	ILevel getCurrentLevel();
	void nextLevel();

}
