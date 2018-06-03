package controller;

import ecs.Engine;
import model.components.BishopAIComponent;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class BishopAISystem extends CustomSystem{
	{
		targets.add(BishopAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
