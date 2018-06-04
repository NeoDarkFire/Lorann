package controller;

import ecs.Engine;
import ecs.Entity;
import model.Direction;
import model.Movement;
import model.components.*;
import view.Action;
import view.IView;

public class UserInputSystem extends CustomSystem {
	{
		targets.add(InputComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		targets.add(SpellCasterComponent.class);
	}
	
	public UserInputSystem() {
		super();
	}
	
	public UserInputSystem(IController controller) {
		super(controller);
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		final IView view = this.controller.getView();
		
		MoveComponent move;
		PositionComponent pos;
		SpellCasterComponent spellCaster;
		
		for (final Entity e : this) {
			move = e.get(MoveComponent.class);
			
			final Direction dir = view.getInputDirection();
			
			move.movement.setDirection(dir);
			spellCaster = e.get(SpellCasterComponent.class);
			if (dir != Direction.NONE) {
				spellCaster.spellMovement.setDirection(dir);
				spellCaster.spellMovement.reverse();
			}
			
			final int nOfSpell = engine.getEntitiesWithComponent(SpellComponent.class).size();
			if (view.isPressed(Action.SPELL) && nOfSpell >= 0 && spellCaster.spellMovement.getDirection() != Direction.NONE) {
				final Entity spell = this.controller.getModel().createSpell();
				final PositionComponent spellPos = spell.get(PositionComponent.class);
				final MoveComponent spellMove = spell.get(MoveComponent.class);
				
				pos = e.get(PositionComponent.class);
				spellPos.pos.x = pos.pos.x;
				spellPos.pos.y = pos.pos.y;
				spellMove.movement = new Movement(spellCaster.spellMovement.getDirection());
				
				engine.addEntity(spell);
			}
		}
	}
}
