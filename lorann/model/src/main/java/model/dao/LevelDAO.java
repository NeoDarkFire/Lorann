package model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.CellData;
import model.MapData;

public abstract class LevelDAO extends AbstractDAO {

	final static private String sqlMapWithID = "{call findMapWithID(?)}";
	final static private String sqlCellsByMapID = "{call findCellsByMapID}";

	public static MapData getMapWithID(int id) throws SQLException {
		final CallableStatement callStatement = prepareCall(sqlMapWithID);
        MapData mapData = null;
        callStatement.setInt(1, id);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();
            if (result.first()) {
                mapData = new MapData(	result.getInt(MapData.IDCOL),
                						result.getInt(MapData.WIDTHCOL),
                						result.getInt(MapData.HEIGHTCOL)	);
            }
            result.close();
        }
        return mapData;
	}
	
	public static ArrayList<CellData> getCellsByMapID(int id) throws SQLException {
		final ArrayList<CellData> cells = new ArrayList<>();
        final CallableStatement callStatement = prepareCall(sqlCellsByMapID);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();

            for (boolean isResultLeft = result.first(); isResultLeft; isResultLeft = result.next()) {
                cells.add(new CellData(		result.getInt(CellData.IDCOL),
                							result.getInt(CellData.XCOL),
                							result.getInt(CellData.YCOL),
                							(char) result.getObject(CellData.SYMBOLCOL),
                							result.getInt(CellData.IDMAPCOL)				));
            }
            result.close();
        }
        return cells;
	}
	
	public static void saveFromFile (File file) throws FileNotFoundException {
		final Scanner sc = new Scanner(file);
		
		int level_width = 0;
		int level_height = 0;
		int x = 0;
		int y = 0;
		
		for (String line = sc.nextLine(); sc.hasNextLine() ; line = sc.nextLine(), y++) {
			for (x = 0; x < line.length(); x++) {
				
			}
		}
		level_width = x;
		level_height = y+1;
		
		sc.close();
	}
}
