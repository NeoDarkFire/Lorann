package model.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ecs.Entity;
import model.CellData;
import model.Example;
import model.ITile;
import model.MapData;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LevelDAO extends AbstractDAO {

	final static private String sqlMapWithID = "{call findMapWithID(?)}";
	final static private String sqlTilesByMapID = "{call findTilesByMapID}";
	final static private String sqlEntitiesByMapID = "{call findEntitiesByMapID}";

	public MapData getMapWithID(int id) throws SQLException {
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
	
	private ArrayList<CellData> getCellsByMapID(int id, String sql) throws SQLException {
		final ArrayList<CellData> cells = new ArrayList<>();
        final CallableStatement callStatement = prepareCall(sql);
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
	
	public ArrayList<CellData> getTilesByMapID(int id) throws SQLException {
		return this.getCellsByMapID(id, sqlTilesByMapID);
	}
	
	public ArrayList<CellData> getEntitiesByMapID(int id) throws SQLException{
		return this.getCellsByMapID(id, sqlEntitiesByMapID);
	}
	
	public void saveFromFile (File file) {
		
	}
}
