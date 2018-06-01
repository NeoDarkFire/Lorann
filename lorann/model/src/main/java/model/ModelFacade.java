package model;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

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

	@Override
	public ILevel getLevelByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

=======
>>>>>>> refs/heads/master
	@Override
	public void saveFromFile(File file) {
		// TODO Auto-generated method stub
		
	}
<<<<<<< HEAD
	
=======

	@Override
	public ILevel getLevelByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> refs/heads/master
}
