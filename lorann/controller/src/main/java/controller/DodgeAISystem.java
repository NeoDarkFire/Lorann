package controller;

import ecs.Engine;
import model.components.DodgeAIComponent;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class DodgeAISystem extends CustomSystem{

	{
		targets.add(DodgeAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
