package controller;

import java.awt.Point;
import java.util.Collection;

import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.Movement;
import model.TileSolidity;
import model.components.*;

public class TowerAISystem extends CustomSystem{
	{
		targets.add(TowerAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
	}

	public void update(final Engine engine, final int dt) {
		MoveComponent move;
		PositionComponent pos;
		
		final Entity[] targets = new Entity[1];
		engine.getEntitiesWithComponent(PlayerComponent.class).toArray(targets); 
		final Entity target = targets[0];
		final int target_x = target.get(PositionComponent.class).pos.x;
		final int target_y = target.get(PositionComponent.class).pos.y;
		
		int next_x, next_y;
		
		final ILevel level = this.controller.getCurrentLevel();
		
		for (final Entity e : this) {
			move = e.get(MoveComponent.class);
			pos = e.get(PositionComponent.class);
			
			/*
			move.movement.setDirection(new Point(	target_x - pos.pos.x,
													target_y - pos.pos.y	));
			this.validateMove(move);
			
			next_x = pos.pos.x + move.movement.getX();
			next_y = pos.pos.y + move.movement.getY();
			
			// Try 4 directions:
			for (int i=1; i<=4; i++) {
				final ITile tile = level.getTileAt(next_x, next_y);
				final Entity e2 = level.getEntityAt(next_x, next_y);
				// If this tile is occupied:
				if (tile.getSolidity() != TileSolidity.FREE
				|| (e2 != null && e2.hasOne(SolidComponent.class, CollectibleComponent.class))) {
					
				}
			}*/
		}
	}
	
	public Movement[] getMoveOrder(final int x, final int y) {
		Movement[] moves = new Movement[4];
		if (y < 0) {
			if (Math.abs(x) > Math.abs(y)) {
				if (x > 0) {
					moves[1] = new Movement(Direction.R);
					moves[2] = new Movement(Direction.U);
					moves[3] = new Movement(Direction.L);
					moves[4] = new Movement(Direction.D);
				} else {
					moves[1] = new Movement(Direction.L);
					moves[2] = new Movement(Direction.U);
					moves[1] = new Movement(Direction.R);
					moves[2] = new Movement(Direction.D);
				}
			} else {
				if (x > 0) {
					moves[1] = new Movement(Direction.R);
					moves[2] = new Movement(Direction.D);
					moves[1] = new Movement(Direction.L);
					moves[2] = new Movement(Direction.U);
				} else {
					moves[1] = new Movement(Direction.L);
					moves[2] = new Movement(Direction.D);
					moves[1] = new Movement(Direction.R);
					moves[2] = new Movement(Direction.U);
				}
			}
		}
		return moves;
	}
}
