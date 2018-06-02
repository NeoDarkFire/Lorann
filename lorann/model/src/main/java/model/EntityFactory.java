package model;

import ecs.Entity;

public abstract class EntityFactory {

	public static Entity getFromSymbol(char symbol) {
		switch (symbol) {
		case 'X':	return createEnergyBall();
		case 'U':	return createDoor();
		case 'M':	return createMask();
		case '@':	return createLorann();
		case 'A':	return createDemon(1);
		case 'B':	return createDemon(2);
		case 'C':	return createDemon(3);
		case 'D':	return createDemon(4);
		case '1':	return createPurse();
		default:	return null;
		}
	}
	
	public static Entity createLorann() {
		
		return null;
	}
	
	public static Entity createDemon(int type) {
		
		return null;
	}
	
	public static Entity createSpell() {
		
		return null;
	}
	
	public static Entity createMask() {
		
		return null;
	}
	
	public static Entity createDoor() {
		
		return null;
	}
	
	public static Entity createEnergyBall() {
		
		return null;
	}
	
	public static Entity createPurse() {
	
		return null;
	}
}
