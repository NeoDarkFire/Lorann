package controller;

import ecs.Engine;
import ecs.Entity;
import model.components.*;
import view.IView;

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
		final IView view = this.controller.getView();
		
		MoveComponent move;
		
		for (final Entity e : this) {
			move = e.get(MoveComponent.class);
			move.movement.setDirection( view.getInputDirection() );
		}
	}
}
