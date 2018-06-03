package controller;

import ecs.Engine;
import model.components.FollowAIComponent;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class FollowAISystem extends CustomSystem{

	{
		targets.add(FollowAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
