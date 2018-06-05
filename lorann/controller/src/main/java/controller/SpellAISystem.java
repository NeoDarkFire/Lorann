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
		targets.add(PositionComponent.class);
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
				final PositionComponent targetPos = targets[0].get(PositionComponent.class);
				for (final Entity e : this) {
					final PositionComponent pos = e.get(PositionComponent.class);
					final Point dest = new Point(	targetPos.pos.x - pos.pos.x,
													targetPos.pos.y - pos.pos.y		);
					e.get(MoveComponent.class).movement.setDirection(dest);
				}
			}
		}
		else if (view.isReleased(Action.SPELL)) {
			this.listen = true;
		}
	}
}
