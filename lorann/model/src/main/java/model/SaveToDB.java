package model;

import java.io.File;

import model.dao.LevelDAO;

abstract class SaveToDB {

	public static void main(String[] args) {
		ClassLoader classLoader = SaveToDB.class.getClassLoader();
		File file = null;
		
		System.out.println("--------------------------");
		System.out.println("Saving levels to database:");
		System.out.println("--------------------------\n");
		try {
			for (int i=1; i <= 5; i++) {
				file = new File(classLoader.getResource(i + ".lvl").getFile());
				System.out.println("Importing level " + i + "...");
				LevelDAO.saveFromFile(file, i);
				System.out.println("  `--> Done.");
			}
			System.out.println("\n------------------------------");
			System.out.println("Successfully saved all levels!");
			System.out.println("------------------------------");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
