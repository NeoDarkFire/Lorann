package model;

import java.awt.Point;

import model.Direction;
import model.Movement;

class RestrictedMovement extends Movement{

	public RestrictedMovement(Direction dir) {
		super(dir);
	}
	
	public RestrictedMovement (Point vec) {
		super(vec);
	}

	public void rotateLeft() {
		super.rotateLeft();
		super.rotateLeft();
	}
	
	public void rotateRight() {
		super.rotateRight();
		super.rotateRight();	
	}
	
}
