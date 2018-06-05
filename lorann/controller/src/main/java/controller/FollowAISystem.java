package controller;

import java.awt.Point;

import model.Movement;
import model.components.FollowAIComponent;
import model.components.MoveComponent;
import model.components.PositionComponent;

public class FollowAISystem extends EnnemyAISystem {

	{
		targets.add(FollowAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		aggressive = true;
	}
	
	public FollowAISystem(IController controller) {
		super(controller);
	}
	
	public FollowAISystem() {
		super();
	}

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
