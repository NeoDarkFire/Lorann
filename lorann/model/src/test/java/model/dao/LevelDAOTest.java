package model.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LevelDAOTest {
	
	private MapData map;

	@Before
	public void setUp() throws Exception {
		this.map = new MapData(1 , 20 , 12);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetMapWithID() {
        MapData expected = new MapData();
        assertEquals(expected, this.map.getId());
	}

}
