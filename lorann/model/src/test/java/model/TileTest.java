package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TileTest {

	private Tile tile;
	
	@Before
	public void setUp() throws Exception {
		this.tile = new Tile("empty.png", TileSolidity.FREE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetImage() {
		if (this.tile.getImage() == null) {
			fail("failed to load image.");
		}
	}

	@Test
	public void testGetSolidity() {
		assertEquals(TileSolidity.FREE, this.tile.getSolidity());
	}

}
