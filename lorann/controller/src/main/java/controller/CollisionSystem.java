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
			this.processEntity(engine, e, dt);
		}
	}
	
	/**
	 * 
	 * @param engine
	 * @param e
	 * @param dt
	 * @param i Number of iteration.
	 */
	private void processEntity(final Engine engine, final Entity e, final double dt) {
		final MoveComponent move;
		final PositionComponent position;
		move = e.get(MoveComponent.class);
		position = e.get(PositionComponent.class);
		
		ITile tile;
		int nOfBounce;
		CollisionType col;
		while (true) {
			if ( !engine.hasEntity(e) ) return;
			nOfBounce = 0;
			// Colliding with a tile
			for (int i=0; i<3; i++) {
				tile = this.getTileFor(e);
				col = this.checkCollision(e, tile);
				if (col == CollisionType.BOUNCE) nOfBounce++;
				else if (col == null) break;
			}
		
			// Colliding with entities
			final Entity e2 = this.getEntityFor(e);
			if ( !engine.hasEntity(e) ) return;
			if ( !engine.hasEntity(e2) || e == e2);
			else if ( this.collectSpell(e, e2) );
			else if ( this.exitLevel(e, e2) );
			else if ( this.collectItem(e, e2) );
			else if ( this.killEntity(e, e2) );
			else if ( this.checkCollision(e, e2) == CollisionType.BOUNCE) nOfBounce++;
			
			if (nOfBounce > 1) {
				move.movement.setDirection(Direction.NONE);
			}
			else if (nOfBounce == 0) break;
		}
		
		// Apply position		
		position.pos.x += move.movement.getX();
		position.pos.y += move.movement.getY();
	}

	private ITile getTileFor(final Entity e) {
		// Get Level
		final ILevel level = this.controller.getCurrentLevel();
		// Get Components
		final MoveComponent move = e.get(MoveComponent.class);
		final PositionComponent pos = e.get(PositionComponent.class);
		// Get potential next position:
		final int next_x = pos.pos.x + move.movement.getX();
		final int next_y = pos.pos.y + move.movement.getY();
		// Get the Tile
		return level.getTileAt(next_x, next_y);
	}
	
	private Entity getEntityFor(final Entity e) {
		// Get Level
		final ILevel level = this.controller.getCurrentLevel();
		// Get Components
		final MoveComponent move = e.get(MoveComponent.class);
		final PositionComponent pos = e.get(PositionComponent.class);
		// Get potential next position:
		final int next_x = pos.pos.x + move.movement.getX();
		final int next_y = pos.pos.y + move.movement.getY();
		// Get the Entities
		return level.getEntityAt(next_x, next_y);
	}
	
	private CollisionType checkCollision(final Entity e, final ITile tile) {
		if (tile.getSolidity() != TileSolidity.FREE) {
			return this.collide(e.get(SolidComponent.class), e.get(MoveComponent.class));
		}
		return null;
	}
	
	private CollisionType checkCollision(final Entity e, final Entity e2) {
		if (e2.has(SolidComponent.class)) {
			java.lang.System.out.println("Collision between " + e + " & " + e2);
			return this.collide(e.get(SolidComponent.class), e.get(MoveComponent.class));
		}
		return null;
	}
	
	private CollisionType collide(SolidComponent solid, MoveComponent move) {
		switch (solid.type) { 
		case STOP:		move.movement.setDirection(Direction.NONE);
						return CollisionType.STOP;
		case BOUNCE:	move.movement.reverse();
						return CollisionType.BOUNCE;
		}
		return null;
	}
	
	private boolean collectSpell(Entity e, Entity e2) {
		if (e.has(SpellComponent.class) && e2.has(SpellCasterComponent.class)) {
			java.lang.System.out.println("Collected spell " + e2);
			e.destroy();
			return true;
		}
		else if (e2.has(SpellComponent.class) && e.has(SpellCasterComponent.class)) {
			java.lang.System.out.println("Collected spell " + e);
			e2.destroy();
			return true;
		}
		return false;
	}
	
	private boolean exitLevel(Entity e, Entity e2) {
		if (e.has(PlayerComponent.class) && e2.has(ExitComponent.class) && !e2.has(DemonComponent.class)) {
			java.lang.System.out.println("Exit Level");
			e2.destroy();
			return true;
		}
		else if (e2.has(PlayerComponent.class) && e.has(ExitComponent.class) && !e.has(DemonComponent.class)) {
			java.lang.System.out.println("Exit Level");
			e.destroy();
			return true;
		}
		return false;
	}
	
	// TODO: Maybe add a CollecterComponent
	// TODO: Maybe handle score
	private boolean collectItem(Entity e, Entity e2) {
		if (e.has(PlayerComponent.class) && e2.has(CollectibleComponent.class)) {
			java.lang.System.out.println("Collected item " + e2);
			e2.destroy();
			return true;
		}
		else if (e2.has(PlayerComponent.class) && e.has(CollectibleComponent.class)) {
			java.lang.System.out.println("Collected item " + e);
			e.destroy();
			return true;
		}
		return false;
	}
	
	private boolean killEntity(Entity e, Entity e2) {
		if (e2.has(KillableComponent.class) && e.has(e2.get(KillableComponent.class).weakness)) {
			e2.destroy();
			java.lang.System.out.println("Killed entity " + e2);
			if (e.has(SpellComponent.class)) {
				e.destroy();
				java.lang.System.out.println("Killed spell " + e);
			}
			return true;
		}
		else if (e.has(KillableComponent.class) && e2.has(e.get(KillableComponent.class).weakness)) {
			e.destroy();
			java.lang.System.out.println("Killed entity " + e);
			if (e2.has(SpellComponent.class)) {
				e2.destroy();
				java.lang.System.out.println("Killed spell " + e2);
			}
			return true;
		}
		return false;
	}
}
