package controller;

import ecs.Engine;
import ecs.Entity;
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

	@Override
	public void update(final Engine engine, final double dt) {
		
		AnimationComponent animation;
		DrawableComponent drawable;
		
		for (final Entity e : this) {
			animation = e.get(AnimationComponent.class);
			drawable = e.get(DrawableComponent.class);
			
			if(animation.play == true) {
				animation.id++;
				if (animation.id >= animation.images.size()) {
					animation.id = 0;
				}
			}
			drawable.image = animation.images.get(animation.id);
		}
		
	}
}
