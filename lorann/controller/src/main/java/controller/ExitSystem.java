package controller;

import ecs.Engine;
import ecs.Entity;
import model.ILevel;
import model.components.*;

public class ExitSystem extends CustomSystem {

	private boolean exit;
	
	{
		targets.add(ExitComponent.class);
		excluded.add(DemonComponent.class);
	}
	
	public ExitSystem(IController controller) {
		super(controller);
	}
	
	public ExitSystem() {
		super();
	}

	@Override
	public void onEntityRemoved(final Entity e) {
		exit = true;
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		if (exit) {
			this.controller.nextLevel();
			exit = false;
		}
	}
}
