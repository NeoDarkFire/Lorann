package controller;

import java.util.Collection;

import ecs.Component;
import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.Movement;
import model.TileSolidity;
import model.components.CollectibleComponent;
import model.components.KillableComponent;
import model.components.MoveComponent;
import model.components.PlayerComponent;
import model.components.PositionComponent;
import model.components.SolidComponent;

public abstract class EnnemyAISystem extends CustomSystem {
	
	protected boolean aggressive;
	protected Collection<Class<? extends Component>> forbidden;
	
	public EnnemyAISystem(IController controller) {
		super(controller);
	}
	
	public EnnemyAISystem() {
		super();
	}

	@Override
	public void update(final Engine engine, final double dt) {
		MoveComponent move;
		PositionComponent pos;
		
		final Entity[] targets = new Entity[1];
		engine.getEntitiesWithComponent(PlayerComponent.class).toArray(targets); 
		final Entity target = targets[0];
		
		final ILevel level = this.controller.getCurrentLevel();
		
		for (final Entity e : this) {
			move = e.get(MoveComponent.class);
			pos = e.get(PositionComponent.class);
			
			// Reset the movement:
			move.movement.setDirection(Direction.NONE);
			
			// Get the target position:
			int target_x = 0, target_y = 0;
			if (target != null) {
				target_x = target.get(PositionComponent.class).pos.x - pos.pos.x;
				target_y = target.get(PositionComponent.class).pos.y - pos.pos.y;
			}
			
			// Get all the possible movement, sorted by priority:
			final Movement[] moves = this.getMoveOrder(target_x, target_y);
			
			// Try every movements:
			for (final Movement movement : moves) {
				// Get potential next position:
				final int next_x = pos.pos.x + movement.getX();
				final int next_y = pos.pos.y + movement.getY();
				
				// Get stuff potentially already there:
				final ITile tile = level.getTileAt(next_x, next_y);
				final Entity e2 = level.getEntityAt(next_x, next_y);
				
				// The movement allows a kill:
				if (this.aggressive && e2 != null && e2.has(KillableComponent.class)
				&&  e.has(e2.get(KillableComponent.class).weakness)) {
					move.movement = movement;
					break;
				}
				// Check if this tile is occupied:
				else if (tile.getSolidity() != TileSolidity.FREE
				|| (e2 != null && e2.hasOne(SolidComponent.class, CollectibleComponent.class))) {
					continue;
				} 
				// The tile is free:
				else {
					move.movement = movement;
					break;
				}
			}
		}
	}
	
	protected Movement[] getMoveOrder(final int x, final int y) {
		return new Movement[0];
	}
}
