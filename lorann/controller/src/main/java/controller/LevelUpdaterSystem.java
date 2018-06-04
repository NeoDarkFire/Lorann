package controller;

import ecs.Component;
import ecs.Entity;
import model.ILevel;
import view.IView;

public class LevelUpdaterSystem extends CustomSystem{
	{
		targets.add(Component.class);
	}
	
	public LevelUpdaterSystem(IController controller) {
		super(controller);
	}
	
	public LevelUpdaterSystem() {
		super();
	}
	
	public void onEntityAdded(final Entity e) {
		final ILevel level = this.controller.getCurrentLevel();
		final IView view = this.controller.getView();
		
		level.addEntity(e);
		view.addSprite(e);

	}
	
	public void onEntityRemoved(Entity e) {
		final ILevel level = this.controller.getCurrentLevel();
		final IView view = this.controller.getView();
		
		level.removeEntity(e);
		view.removeSprite(e);
	}
}
