package controller;

import java.awt.List;
import java.awt.Point;

import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.Movement;
import model.TileSolidity;
import model.components.BishopAIComponent;
import model.components.CollectibleComponent;
import model.components.DemonComponent;
import model.components.KillableComponent;
import model.components.MoveComponent;
import model.components.PlayerComponent;
import model.components.PositionComponent;
import model.components.SolidComponent;

public class BishopAISystem extends CustomSystem{
	{
		targets.add(BishopAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}
	
	public BishopAISystem(IController controller) {
		super(controller);
	}
	
	public BishopAISystem() {
		super();
	}

	// TODO: /!\ WARNING: update is the same as TowerAISystem /!\
	@Override
	public void update(final Engine engine, final double dt) {
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
		Direction first, second;
		// If it's above:
		if (y < 0) {
			// If it's to the left:
			if (x < 0) {
				first = Direction.UL;
				second = (x < y) ? Direction.DL : Direction.UR;
			}
			// If it's to the right:
			else {
				first = Direction.UR;
				second = (-x < y) ? Direction.DR : Direction.UL;
			}
		}
		// If it's below:
		else {
			// If it's to the left:
			if (x < 0) {
				first = Direction.DL;
				second = (x < -y) ? Direction.UL : Direction.DR;
			}
			// If it's to the right:
			else {
				first = Direction.DR;
				second = (x > y) ? Direction.UR : Direction.DL;
			}
		}
		moves[0] = new Movement(first);
		moves[1] = new Movement(second);
		moves[2] = new Movement(second);
		moves[2].reverse();
		moves[3] = new Movement(first);
		moves[3].reverse();
		return moves;
	}
}
