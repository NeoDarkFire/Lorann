package controller;

import java.awt.Point;

import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.Movement;
import model.TileSolidity;
import model.components.*;

public class DodgeAISystem extends CustomSystem{

	{
		targets.add(DodgeAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}
	
	public DodgeAISystem(IController controller) {
		super(controller);
	}
	
	public DodgeAISystem() {
		super();
	}

	// TODO: parameterise an abstract system to avoid copy-pasted code.
	
	// TODO: /!\ WARNING: update is the same as TowerAISystem /!\
	public void update(final Engine engine, final int dt) {
		MoveComponent move;
		PositionComponent pos;
		
		final Entity[] targets = new Entity[1];
		engine.getEntitiesWithComponent(PlayerComponent.class).toArray(targets); 
		final Entity target = targets[0];
		final int target_x = target.get(PositionComponent.class).pos.x;
		final int target_y = target.get(PositionComponent.class).pos.y;
		
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
				// (note:	The AI will ALWAYS dodge the spell, unless no move is possible.
				// 			However, the AI will also NEVER kill the player by itself.		)
				if (tile.getSolidity() != TileSolidity.FREE
				|| (e2 != null && e2.hasOne(SolidComponent.class, CollectibleComponent.class, SpellComponent.class))) {
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
	
	// TODO: WARNING /!\ Same as FollowAISystem /!\
	protected Movement[] getMoveOrder(final int x, final int y) {
		Movement[] moves = new Movement[8];
		moves[0] = new Movement(new Point(x, y));
		for (int i=1; i < 8; i++) {
			moves[i] = new Movement(moves[Math.max(0, i-2)].getDirection());
			if (i%2 == 0) {
				moves[i].rotateRight();
			}
			else {
				moves[i].rotateLeft();
			}
		}
		return moves;
	}

}
