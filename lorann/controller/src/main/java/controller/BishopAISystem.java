package controller;

import model.Direction;
import model.Movement;
import model.components.BishopAIComponent;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class BishopAISystem extends EnnemyAISystem {
	{
		targets.add(BishopAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		aggressive = true;
	}
	
	public BishopAISystem(IController controller) {
		super(controller);
	}
	
	public BishopAISystem() {
		super();
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
