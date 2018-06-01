package controller;

import java.util.HashSet;
import java.util.Set;

public class LevelUpdaterSystem extends CustomSystem{

	protected static Set<Class<? extends Component>> targets = new HashSet<>();
	{
		targets.add(Component.class);
	}
	
	public void onEntityAdded(Entity e) {
		
	}
	
	public void onEntityRemoved(Entity e) {
		
	}
}
