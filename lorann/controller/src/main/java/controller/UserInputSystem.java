package controller;

import ecs.Engine;
import model.components.*;

public class UserInputSystem extends CustomSystem {
	{
		targets.add(InputComponent.class);
		targets.add(MoveComponent.class);
		excluded.add(SpellAIComponent.class);
	}
	
	public UserInputSystem() {
		super();
	}
	
	public UserInputSystem(IController controller) {
		super(controller);
	}
	
	public void update(Engine engine, int dt) {
		
	}
}
