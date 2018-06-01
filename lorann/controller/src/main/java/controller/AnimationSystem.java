package controller;

import java.util.HashSet;
import java.util.Set;

public class AnimationSystem extends CustomSystem{

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(DrawableComponent.class);
		targets.add(AnimationComponent.class);
	}

	public void update(Engine engine, int dt) {
		
	}
}
