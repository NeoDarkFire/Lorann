package model.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

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
	public void testSaveFromFile() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = "level_tst.txt";
		File file = new File(classLoader.getResource(fileName).getFile());
        LevelDAO.saveFromFile(file);
	}

}
