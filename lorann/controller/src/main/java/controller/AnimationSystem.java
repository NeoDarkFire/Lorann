package controller;

import ecs.Engine;
import model.components.AnimationComponent;
import model.components.DrawableComponent;

public class AnimationSystem extends CustomSystem{

	{
		targets.add(DrawableComponent.class);
		targets.add(AnimationComponent.class);
	}
	
	public AnimationSystem(IController controller) {
		super(controller);
	}
	
	public AnimationSystem() {
		super();
	}

	public void update(Engine engine, int dt) {
		
	}
}
