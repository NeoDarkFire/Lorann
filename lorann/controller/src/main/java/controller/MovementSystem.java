package controller;

import ecs.Engine;
import ecs.Entity;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class MovementSystem extends CustomSystem{
	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}
	
	public MovementSystem(IController controller) {
		super(controller);
	}
	
	public MovementSystem() {
		super();
	}

	@Override
	public void update(final Engine engine, final double dt) {
		MoveComponent move;
		PositionComponent position;
		int x,y;
		
		for(final Entity e : this) {
			
			move = e.get(MoveComponent.class);
			position = e.get(PositionComponent.class);
			
			x = move.movement.getX();
			y = move.movement.getY();
			
			position.pos.x += x;
			position.pos.y += y;
		
		}
	}
}
