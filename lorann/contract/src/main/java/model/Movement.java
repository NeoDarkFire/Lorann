package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class Movement {
	
//----------------------ATTRIBUTES--------------------
	/**
	 * Associates a Direction with a Point.
	 */
	private final static HashMap<Direction, Point> MAP = new HashMap<>();
	private final static ArrayList<Direction> LIST = new ArrayList<>();
	private final static HashMap<Double, Direction> ANGLE = new HashMap<>();
	
	static {
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
		
		ANGLE.put(0.0, 		Direction.R);
		ANGLE.put(1.0/4.0, 	Direction.UR);
		ANGLE.put(1.0/2.0, 	Direction.U);
		ANGLE.put(3.0/4.0, 	Direction.UL);
		ANGLE.put(1.0, 		Direction.L);
		ANGLE.put(-3.0/4.0, Direction.DL);
		ANGLE.put(-1.0/2.0, Direction.D);
		ANGLE.put(-1.0/4.0, Direction.DR);
	}
	
	private Direction dir;
	
//----------------------METHODS---------------------	
	public Movement(Direction dir) {
		this.setDirection(dir);
	}
	
	public Movement(Point vec) {
		this.setDirection(vec);
	}
	
	public int getX() {
		return (int) MAP.get(dir).getX();
	}
	
	public int getY() {
		return (int) MAP.get(dir).getY();
	}
	
	public void rotateLeft() {
		Direction dir;
		int i = LIST.indexOf(this.dir)-1;
		if (i < 0) {
			i = LIST.size()-1;
		}
		dir = LIST.get(i);
		this.setDirection(dir);
	}
	
	public void rotateRight() {
		Direction dir;
		int i = LIST.indexOf(this.dir)+1;
		if (i > 7) {
			i = 0;
		}
		dir = LIST.get(i);
		this.setDirection(dir);		
	}
	
	public void reverse() {
		Direction dir;
		int i = LIST.indexOf(this.dir)+4;
		if (i > 7) {
			i -= 8;
		}
		dir = LIST.get(i);
		this.setDirection(dir);
		
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public void setDirection(Point vec) {
		double a = Math.atan2(-vec.getY(), vec.getX()) / Math.PI;
		double d = 1.0/8.0;
		for (final Double angle : ANGLE.keySet()) {
			if ( a > (angle-d) && a < (angle+d)) {
				this.setDirection(ANGLE.get(angle));
				return;
			}
		}
	}
	
	public Direction getDirection() {
		return this.dir;
	}

}
