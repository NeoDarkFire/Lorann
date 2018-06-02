package model.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.MapData;

public class LevelDAOTest {
	
	private MapData map;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDataFromFile() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = "level_tst.txt";
		File file = new File(classLoader.getResource(fileName).getFile());
        
		Map<Class<?>, Object> data = LevelDAO.getDataFromFile(file);
        
		MapData map = (MapData) data.get(MapData.class);
		assertEquals(0, map.getID());
		assertEquals(20, map.getWidth());
		assertEquals(12, map.getHeight());
	}

}
