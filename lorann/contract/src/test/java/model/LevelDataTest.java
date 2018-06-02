package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LevelDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLevelDataFromFile() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("level_tst.txt").getFile());
        
		LevelData levelData = new LevelData(file);
        
		MapData map = levelData.getMap();
		assertEquals(0, map.getID());
		assertEquals(20, map.getWidth());
		assertEquals(12, map.getHeight());
		
		List<CellData> cells = levelData.getGrid();
		assertEquals('O', cells.get(0).getSymbol());
		assertEquals(20*12, cells.size());
	}

}
