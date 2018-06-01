package controller;

import java.util.HashSet;
import java.util.Set;

public class FollowAISystem extends CustomSystem{

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(FollowAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
