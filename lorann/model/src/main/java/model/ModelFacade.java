package model;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import model.dao.ExampleDAO;

/**
 * <h1>The Class ModelFacade provides a facade of the Model component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public final class ModelFacade implements IModel {

    /**
     * Instantiates a new model facade.
     */
    public ModelFacade() {
        super();
    }

<<<<<<< HEAD
	@Override
	public List<Example> getAllExamples() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
=======
    /*
     * (non-Javadoc)
     * @see model.IModel#getExampleById(int)
     */
    @Override
    public Ilevel getLevelById(final int id) throws SQLException {
        return null;
    }
>>>>>>> refs/heads/model

<<<<<<< HEAD
	@Override
	public ILevel getLevelByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
=======
    /*
     * (non-Javadoc)
     * @see model.IModel#getExampleById(int)
     */
    @Override
    public void saveFromFile(File file) throws SQLException {
>>>>>>> refs/heads/model

<<<<<<< HEAD
	@Override
	public void saveFromFile(File file) {
		// TODO Auto-generated method stub
		
	}

=======
    }
>>>>>>> refs/heads/model
}
