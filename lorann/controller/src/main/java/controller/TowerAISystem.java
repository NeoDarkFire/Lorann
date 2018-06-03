package controller;

import ecs.Engine;
import model.components.MoveComponent;
import model.components.PositionComponent;
import model.components.TowerAIComponent;

public class TowerAISystem extends CustomSystem{
	{
		targets.add(TowerAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
