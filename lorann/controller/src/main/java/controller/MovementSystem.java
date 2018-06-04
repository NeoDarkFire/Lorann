package controller;

import ecs.Engine;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class MovementSystem extends CustomSystem{
	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
