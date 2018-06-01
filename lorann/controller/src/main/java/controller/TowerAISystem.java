package controller;

import java.util.HashSet;
import java.util.Set;

import ecs.Component;
import ecs.Engine;
import model.components.MoveComponent;
import model.components.PositionComponent;
import model.components.TowerAIComponent;

public class TowerAISystem extends CustomSystem{

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(TowerAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
