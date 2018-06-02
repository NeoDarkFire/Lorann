package model.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.CellData;
import model.LevelData;
import model.MapData;

public class LevelDAOTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDataFromFile() throws FileNotFoundException, SQLException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = "level_tst.txt";
		File file = new File(classLoader.getResource(fileName).getFile());
        
		LevelData levelData = LevelDAO.getDataFromFile(file);
        
		MapData map = levelData.getMap();
		assertEquals(0, map.getID());
		assertEquals(20, map.getWidth());
		assertEquals(12, map.getHeight());
		
		List<CellData> cells = levelData.getGrid();
		assertEquals('O', cells.get(0).getSymbol());
		assertEquals(20*12, cells.size());
	}

}
