package controller;

import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.Movement;
import model.TileSolidity;
import model.components.*;

public class TowerAISystem extends CustomSystem {
	{
		targets.add(TowerAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}
	
	public TowerAISystem(IController controller) {
		super(controller);
	}
	
	public TowerAISystem() {
		super();
	}

	public void update(final Engine engine, final int dt) {
		MoveComponent move;
		PositionComponent pos;
		
		final Entity[] targets = new Entity[1];
		engine.getEntitiesWithComponent(PlayerComponent.class).toArray(targets); 
		final Entity target = targets[0];
		
		int target_x = 0, target_y = 0;
		if (target != null) {
			target_x = target.get(PositionComponent.class).pos.x;
			target_y = target.get(PositionComponent.class).pos.y;
		}
		
		final ILevel level = this.controller.getCurrentLevel();
		
		for (final Entity e : this) {
			move = e.get(MoveComponent.class);
			pos = e.get(PositionComponent.class);
			
			// Reset the movement:
			move.movement.setDirection(Direction.NONE);
			// Get all the possible movement, sorted by priority:
			final Movement[] moves = this.getMoveOrder(		target_x - pos.pos.x,
															target_y - pos.pos.y	);
			// Try every movements:
			for (final Movement movement : moves) {
				// Get potential next position:
				final int next_x = pos.pos.x + movement.getX();
				final int next_y = pos.pos.y + movement.getY();
				// Get stuff potentially already there:
				final ITile tile = level.getTileAt(next_x, next_y);
				final Entity e2 = level.getEntityAt(next_x, next_y);
				
				// Check if this tile is occupied:
				if (tile.getSolidity() != TileSolidity.FREE
				|| (e2 != null && e2.hasOne(SolidComponent.class, CollectibleComponent.class))) {
					continue;
				}
				// The movement allows a kill:
				else if (e2 != null && e2.has(KillableComponent.class)
					 &&  e2.get(KillableComponent.class).weakness == DemonComponent.class) {
					move.movement = movement;
					break;
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
		Movement[] moves = new Movement[4];
		int u = 0, l = 0, d = 0, r = 0;  // The direction priorities (1: best, 4: worst)
		// If the target is above:
		if (y < 0) {
			// If it's more horizontal than vertical:
			if (Math.abs(x) > Math.abs(y)) {
				u = 2;
				d = 3;
				// If it's to the right:
				if (x > 0) {
					r = 1;
					l = 4;
				} else {
					l = 1;
					r = 4;
				}
			}
			// If it's more vertical:
			else {
				u = 1;
				d = 4;
				// If it's to the right:
				if (x > 0) {
					r = 2;
					l = 3;
				} else {
					l = 2;
					r = 3;
				}
			}
		}
		// If the target is below:
		else {
			// If it's more horizontal than vertical:
			if (Math.abs(x) > Math.abs(y)) {
				d = 2;
				u = 3;
				// If it's to the right:
				if (x > 0) {
					r = 1;
					l = 4;
				} else {
					l = 1;
					r = 4;
				}
			}
			// If it's more vertical:
			else {
				d = 1;
				u = 4;
				// If it's to the right:
				if (x > 0) {
					r = 2;
					l = 3;
				} else {
					l = 2;
					r = 3;
				}
			}
		}
		moves[u-1] = new Movement(Direction.U);
		moves[l-1] = new Movement(Direction.L);
		moves[d-1] = new Movement(Direction.D);
		moves[r-1] = new Movement(Direction.R);
		return moves;
	}
}
