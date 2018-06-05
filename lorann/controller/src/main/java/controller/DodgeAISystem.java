package controller;

import java.awt.Point;
import model.Direction;
import model.Movement;
import model.components.*;

public class DodgeAISystem extends EnnemyAISystem {

	{
		targets.add(DodgeAIComponent.class);
		targets.add(MoveComponent.class);
		targets.add(PositionComponent.class);
		aggressive = false;
	}
	
	public DodgeAISystem(IController controller) {
		super(controller);
	}
	
	public DodgeAISystem() {
		super();
	}
	
	protected Movement[] getMoveOrder(final int x, final int y) {
		Movement[] moves = new Movement[8];
		moves[0] = new Movement(new Point(x, y));
		if (moves[0].getDirection() == Direction.NONE) {
			moves[0].setDirection(Direction.U);
		}
		for (int i=1; i < 8; i++) {
			moves[i] = new Movement(moves[Math.max(0, i-2)].getDirection());
			if (i%2 == 0) {
				moves[i].rotateLeft();
			}
			else {
				moves[i].rotateRight();
			}
		}
		return moves;
	}

}
