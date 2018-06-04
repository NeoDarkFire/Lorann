package controller;

import ecs.Engine;
import ecs.Entity;
import model.ILevel;
import model.components.*;

public class GateSystem extends CustomSystem {

	private Entity door;
	
	{
		targets.add(EnergyBallComponent.class);
		targets.add(PositionComponent.class);
	}
	
	public GateSystem(IController controller) {
		super(controller);
	}
	
	public GateSystem() {
		super();
	}

	@Override
	public void onEntityRemoved(final Entity e) {
		e.destroy();
		
		ILevel level = this.controller.getCurrentLevel();
		for (final Entity e2 : level.getEntities()) {
			if (e2.hasAll(ExitComponent.class, DemonComponent.class)) {
				final PositionComponent maskPos = e2.get(PositionComponent.class);
				this.door = this.controller.getModel().createDoor();
				final PositionComponent doorPos = this.door.get(PositionComponent.class);
				
				doorPos.pos.x = maskPos.pos.x;
				doorPos.pos.y = maskPos.pos.y;
				
				e2.destroy();
				return;
			}
		}
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		if (this.door != null) {
			engine.addEntity(this.door);
			this.door = null;
		}
	}
}
