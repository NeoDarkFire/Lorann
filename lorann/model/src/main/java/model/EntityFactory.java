package model;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ecs.Component;
import ecs.Entity;

import model.components.*;

public abstract class EntityFactory {
	
	static ClassLoader classLoader = EntityFactory.class.getClassLoader();

	public static Entity getFromSymbol(final char symbol) {
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
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		MoveComponent move = new MoveComponent();
		move.movement = new Movement(Direction.NONE);
		
		PlayerComponent player = new PlayerComponent();
		
		InputComponent input = new InputComponent();
		
		KillableComponent killable = new KillableComponent();
		killable.weakness = DemonComponent.class;
		
		DrawableComponent drawable = new DrawableComponent();
		
		AnimationComponent animation = new AnimationComponent();
		animation.images = new ArrayList<>();
		try {
			String[] dirs = {"u", "ur", "r", "dr", "d", "dl", "l", "ul"};
			for (final String dir : dirs) {
				animation.images.add(ImageIO.read(new File(classLoader.getResource("lorann_" + dir + ".png").getFile())));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		animation.id = 0;
		animation.play = true;
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;
		
		SpellCasterComponent spellCaster = new SpellCasterComponent();
		spellCaster.spellMovement = new Movement(Direction.NONE);
		
		e.attach(pos);
		e.attach(move);
		e.attach(player);
		e.attach(input);
		e.attach(killable);
		e.attach(drawable);
		e.attach(animation);
		e.attach(spellCaster);
		e.attach(solid);
		
		return e;
	}
	
	public static Entity createDemon(final int type) {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		MoveComponent move = new MoveComponent();
		switch (type) {
		case 1: move.movement = new RestrictedMovement(Direction.NONE); break;
		case 2: move.movement = new Movement(Direction.NONE); break;
		case 3: move.movement = new Movement(Direction.NONE); break;
		case 4: move.movement = new RestrictedMovement(Direction.NONE); break;
		}
		
		DemonComponent demon = new DemonComponent();
		
		KillableComponent killable = new KillableComponent();
		killable.weakness = SpellComponent.class;
		
		DrawableComponent drawable = new DrawableComponent();
		try {
			drawable.image = ImageIO.read(new File(classLoader.getResource("demon_" + type + ".png").getFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;

		Component ai = null;
		switch (type) {
		case 1: ai = new BishopAIComponent(); break;
		case 2: ai = new DodgeAIComponent(); break;
		case 3: ai = new FollowAIComponent(); break;
		case 4: ai = new TowerAIComponent(); break;
		}
		
		e.attach(demon);
		e.attach(pos);
		e.attach(move);
		e.attach(killable);
		e.attach(drawable);
		e.attach(solid);
		e.attach(ai);
		
		return e;
	}
	
	public static Entity createSpell() {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		MoveComponent move = new MoveComponent();
		move.movement = new Movement(Direction.NONE);
		
		SpellComponent spell = new SpellComponent();
		
		DrawableComponent drawable = new DrawableComponent();
		
		AnimationComponent animation = new AnimationComponent();
		animation.images = new ArrayList<>();
		try {
			for (int i = 1; i <= 5; i++) {
				animation.images.add(ImageIO.read(new File(classLoader.getResource("spell_" + i + ".png").getFile())));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		animation.id = 0;
		animation.play = true;
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.BOUNCE;

		SpellAIComponent ai = new SpellAIComponent();
		
		InputComponent input = new InputComponent();
		
		e.attach(spell);
		e.attach(pos);
		e.attach(move);
		e.attach(drawable);
		e.attach(animation);
		e.attach(solid);
		e.attach(ai);
		e.attach(input);
		
		return e;
	}
	
	public static Entity createMask() {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		DemonComponent demon = new DemonComponent();
		
		DrawableComponent drawable = new DrawableComponent();
		try {
			drawable.image = ImageIO.read(new File(classLoader.getResource("gate_closed.png").getFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;
		
		ExitComponent exit = new ExitComponent();
		
		e.attach(demon);
		e.attach(pos);
		e.attach(drawable);
		e.attach(solid);
		e.attach(exit);
		
		return e;
	}
	
	public static Entity createDoor() {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		DrawableComponent drawable = new DrawableComponent();
		try {
			drawable.image = ImageIO.read(new File(classLoader.getResource("gate_open.png").getFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;
		
		ExitComponent exit = new ExitComponent();
		
		solid.type = CollisionType.STOP;
		
		e.attach(pos);
		e.attach(drawable);
		e.attach(exit);
		e.attach(solid);
		
		return e;
	}
	
	public static Entity createEnergyBall() {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		DrawableComponent drawable = new DrawableComponent();
		try {
			drawable.image = ImageIO.read(new File(classLoader.getResource("energy_ball.png").getFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		CollectibleComponent collectible = new CollectibleComponent();
		
		EnergyBallComponent energyBall = new EnergyBallComponent();
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;
		
		e.attach(pos);
		e.attach(drawable);
		e.attach(collectible);
		e.attach(energyBall);
		e.attach(solid);
		
		
		return e;
	}
	
	public static Entity createPurse() {
		Entity e = new Entity();
		
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point();
		
		DrawableComponent drawable = new DrawableComponent();
		try {
			drawable.image = ImageIO.read(new File(classLoader.getResource("purse.png").getFile()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		CollectibleComponent collectible = new CollectibleComponent();
		collectible.score = 100;
		
		SolidComponent solid = new SolidComponent();
		solid.type = CollisionType.STOP;
		
		e.attach(pos);
		e.attach(drawable);
		e.attach(collectible);
		e.attach(solid);
		
		return e;
	}
}
