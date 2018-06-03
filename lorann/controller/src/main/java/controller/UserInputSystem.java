package controller;

import ecs.Engine;
import model.components.InputComponent;
import model.components.MoveComponent;

public class UserInputSystem extends CustomSystem {
	{
		targets.add(InputComponent.class);
		targets.add(MoveComponent.class);
	}
	
	public void update(Engine engine, int dt) {
		
	}
}
