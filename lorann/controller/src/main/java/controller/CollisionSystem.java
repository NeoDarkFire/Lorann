package controller;

import ecs.Engine;
import ecs.Entity;
import model.CollisionType;
import model.Direction;
import model.ILevel;
import model.ITile;
import model.TileSolidity;
import model.components.*;

public class CollisionSystem extends CustomSystem{

	{
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		targets.add(SolidComponent.class);
	}
	
	public CollisionSystem(IController controller) {
		super(controller);
	}
	
	public CollisionSystem() {
		super();
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		for (final Entity e : this) {
			this.processEntity(engine, e, dt, 1);
		}
	}
	
	/**
	 * 
	 * @param engine
	 * @param e
	 * @param dt
	 * @param i Number of iteration.
	 */
	private void processEntity(final Engine engine, final Entity e, final double dt, final int i) {
		final MoveComponent move;
		final PositionComponent pos;
		final SolidComponent solid;
		
		final ILevel level = this.controller.getCurrentLevel();
		
		move = e.get(MoveComponent.class);
		pos = e.get(PositionComponent.class);
		solid = e.get(SolidComponent.class);
		
		// Get potential next position:
		final int next_x = pos.pos.x + move.movement.getX();
		final int next_y = pos.pos.y + move.movement.getY();
		
		// Get stuff potentially already there:
		final ITile tile = level.getTileAt(next_x, next_y);
		final Entity e2 = level.getEntityAt(next_x, next_y);
		
		// Collecting a spell:
		if (e.has(SpellComponent.class) && e2 != null && e2.has(SpellCasterComponent.class)) {
			e.destroy();
			return;
		}
		
		// Check if this tile is occupied:
		if (tile.getSolidity() != TileSolidity.FREE
		|| (e2 != null && e2.has(SolidComponent.class))) {
			switch (solid.type) { 
			case STOP:		move.movement.setDirection(Direction.NONE);
							return;
			case BOUNCE:	move.movement.reverse();
							// process a second time
							if (i == 1) {
								this.processEntity(engine, e, dt, 2);
							}
							else if (i == 2) {
								move.movement.setDirection(Direction.NONE);
								this.processEntity(engine, e, dt, 3);
							}
							return;
			}
		}
		// The movement allows a collection:
		else if (e2 != null && e2.has(CollectibleComponent.class)) {
			// TODO: handle score
			e2.destroy();
		}
		// The movement allows a kill:
		else if (e2 != null && e2.has(KillableComponent.class)
			 &&  e2.get(KillableComponent.class).weakness == DemonComponent.class) {
			
		}
		// The tile is free:
		else {
			
		}
	}
}
