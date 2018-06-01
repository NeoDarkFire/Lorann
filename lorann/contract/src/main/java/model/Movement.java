package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Movement {
	
//----------------------ATTRIBUTES--------------------
	/**
	 * Associates a Direction with a Point.
	 */
	private final static HashMap<Direction, Point> MAP = new HashMap<>();
	private final static ArrayList<Direction> LIST = new ArrayList<>();
	
	{
		MAP.put(Direction.U, new Point(0, -1));
		MAP.put(Direction.D, new Point(0, 1));
		MAP.put(Direction.L, new Point(-1, 0));
		MAP.put(Direction.R, new Point(1, 0));
		MAP.put(Direction.UL, new Point(-1, -1));
		MAP.put(Direction.UR, new Point(1, -1));
		MAP.put(Direction.DL, new Point(-1, 1));
		MAP.put(Direction.DR, new Point(1, 1));
		MAP.put(Direction.NONE, new Point(0, 0));
		
		LIST.add(Direction.U);
		LIST.add(Direction.UR);
		LIST.add(Direction.R);
		LIST.add(Direction.DR);
		LIST.add(Direction.D);
		LIST.add(Direction.DL);
		LIST.add(Direction.L);
		LIST.add(Direction.UL);
	}
	
//----------------------METHODS---------------------	
	public Movement(Direction dir) {}
	public Movement(Point vec) {}
	public int getX() {
		return 0;
	}
	
	public int getY() {
		return 0;
	}
	
	public void rotateLeft() {}
	public void rotateRight() {}
	public void reverse() {}
	public void setDirection(Direction dir) {}
	public Direction getDirection() {
		return null;
	}

}
