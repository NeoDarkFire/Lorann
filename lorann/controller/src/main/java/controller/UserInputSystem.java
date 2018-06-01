package controller;

import java.util.HashSet;
import java.util.Set;

public class UserInputSystem extends CustomSystem {

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(InputComponent.class);
		targets.add(MoveComponent.class);
	}
	
	public void update(Engine engine, int dt) {
		
	}
}
