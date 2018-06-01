package controller;

import java.util.HashSet;
import java.util.Set;

public class CollisionSystem extends CustomSystem{

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		targets.add(SolidComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
