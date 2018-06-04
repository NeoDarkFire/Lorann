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
	
	public SpellAISystem(IController controller) {
		super(controller);
	}
	
	public SpellAISystem() {
		super();
	}
	
	private boolean listen;
	
	@Override
	public void onEntityAdded(final Entity e) {
		this.listen = false;
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		final IView view = this.controller.getView();
		if (this.listen && view.isPressed(Action.SPELL)) {
			this.listen = false;
			
			final Entity[] targets = new Entity[1];
			engine.getEntitiesWithComponents(PlayerComponent.class, PositionComponent.class).toArray(targets);
			final Entity target = targets[0];
			
			if (target != null) {
				final Point targetPos = targets[0].get(PositionComponent.class).pos; 
				for (final Entity e : this) {
					// TODO: FIX
//					e.get(MoveComponent.class).movement.setDirection(targetPos);
					java.lang.System.out.println(e.get(MoveComponent.class).movement.getDirection());
				}
			}
		}
		else {
			this.listen = true;
		}
	}
}
