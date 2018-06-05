package controller;

import model.Direction;
import model.Movement;
import model.components.*;

public class TowerAISystem extends EnnemyAISystem {
	{
		targets.add(TowerAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		aggressive = true;
	}
	
	public TowerAISystem(IController controller) {
		super(controller);
	}
	
	public TowerAISystem() {
		super();
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
