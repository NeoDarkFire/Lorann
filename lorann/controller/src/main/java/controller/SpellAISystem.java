package controller;

import java.awt.Point;

import ecs.Engine;
import ecs.Entity;
import model.components.*;
import view.Action;
import view.IView;

public class SpellAISystem extends CustomSystem{
	{
		targets.add(SpellAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(InputComponent.class);
	}
	
	private boolean listen;
	
	public void onEntitAdded(final Entity e) {
		this.listen = false;
	}
	
	public void update(Engine engine, int dt) {
		final IView view = this.controller.getView();
		if (this.listen && view.isPressed(Action.SPELL)) {
			this.listen = false;
			
			final Entity[] targets = new Entity[1];
			engine.getEntitiesWithComponents(PlayerComponent.class, PositionComponent.class).toArray(targets);
			final Entity target = targets[0];
			
			if (target != null) {
				final Point targetPos = targets[0].get(PositionComponent.class).pos; 
				for (final Entity e : this) {
					e.get(MoveComponent.class).movement.setDirection(targetPos);
				}
			}
		}
		else {
			this.listen = true;
		}
	}
}
