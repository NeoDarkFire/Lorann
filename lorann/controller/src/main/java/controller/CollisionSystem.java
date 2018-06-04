package controller;

import ecs.Engine;
import model.components.MoveComponent;
import model.components.PositionComponent;
import model.components.SolidComponent;

public class CollisionSystem extends CustomSystem{

	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		targets.add(SolidComponent.class);
	}
	
	public CollisionSystem(IController controller) {
		super(controller);
	}
	
	public CollisionSystem() {
		super();
	}

	public void update(Engine engine, int dt) {
		
	}
}
