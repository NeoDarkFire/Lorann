package model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ecs.Entity;
import model.components.PositionComponent;

public class LevelTest {

	private Level level;
	
	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		this.level = new Level(
				new LevelData(
						new File(
								classLoader.getResource("level_tst.txt").getFile())));
	}

	@After
	public void tearDown() throws Exception {
		this.level = null;
	}

	@Test
	public void testGetTileAt() {
		ITile tile = this.level.getTileAt(0, 0);
		if (TileFactory.getBone() != tile) {
			fail("Wrong tile at position (0 ; 0)");
		}
	}

	@Test
	public void testReload() {
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point(0, 0);
		this.level.addEntity(new Entity(pos));
		this.level.reload();
		Entity e = this.level.getEntityAt(0, 0);
		if (e != null) {
			fail("Did not properly reload a level.");
		}
	}

	@Test
	public void testAddEntity() {
		final int n = this.level.getEntities().size();
		this.level.addEntity(new Entity());
		assertEquals(n+1, this.level.getEntities().size());
	}

	@Test
	public void testRemoveEntity() {
		final int n = this.level.getEntities().size();
		this.level.removeEntity(this.level.getEntityAt(1, 10));
		assertEquals(n-1, this.level.getEntities().size());
	}

	@Test
	public void testGetEntities() {
		final int n = this.level.getEntities().size();
		assertEquals(5 , n);
	}

	@Test
	public void testGetEntityAt() {
		PositionComponent pos = new PositionComponent();
		pos.pos = new Point(0, 0);
		this.level.addEntity(new Entity(pos));
		Entity e = this.level.getEntityAt(0, 0);
		if (e == null) {
			fail("Failed to get an Entity.");
		}
	}

}
