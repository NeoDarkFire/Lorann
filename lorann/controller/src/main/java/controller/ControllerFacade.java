 package controller;

import java.sql.SQLException;
import java.util.List;

import ecs.Engine;
import ecs.Entity;
import model.ILevel;
import model.IModel;
import model.components.PlayerComponent;
import view.IView;

/**
 * <h1>The Class ControllerFacade provides a facade of the Controller component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ControllerFacade implements IController {

    /** The view. */
    private final IView  view;

    /** The model. */
    private final IModel model;
    
    /** The engine. */
    private final Engine engine;
    
    /** The level. */
    private ILevel level;
    
    /** The time of the last update. */
    private long lastTime;

    /**
     * Instantiates a new controller facade.
     *
     * @param view
     *            the view
     * @param model
     *            the model
     */
    public ControllerFacade(final IView view, final IModel model) {
    	this.view = view;
		this.model = model;
		this.engine = new Engine();
    	try {
        	final ILevel level = this.model.getLevelByID(1);
        	this.view.setLevel(level);
			this.initLevel( level );
			
			this.engine.addSystem(new LevelUpdaterSystem(this));
			this.engine.addSystem(new UserInputSystem(this));
			this.engine.addSystem(new SpellAISystem(this));
			this.engine.addSystem(new FollowAISystem(this));
			this.engine.addSystem(new TowerAISystem(this));
			this.engine.addSystem(new DodgeAISystem(this));
			this.engine.addSystem(new BishopAISystem(this));
			this.engine.addSystem(new CollisionSystem(this));
			this.engine.addSystem(new MovementSystem(this));
			this.engine.addSystem(new AnimationSystem(this));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    /**
     * Start.
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Override
    public void start() {
    	long currentTime;
    	long dt;
    	while (true) {
    		currentTime = System.currentTimeMillis();
	    	dt = currentTime - lastTime;
	    	if (dt >= 1000.0/5.0) {
	    		this.lastTime = currentTime;
	    		engine.update((int) (dt/1000));
	    		this.view.refresh();
	    	}
    	}
    }

    /**
     * Gets the view.
     *
     * @return the view
     */
    @Override
    public IView getView() {
        return this.view;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    @Override
    public IModel getModel() {
        return this.model;
    }
    
    @Override
    public ILevel getCurrentLevel() {
    	return this.level;
    }
    
    @Override
    public void nextLevel() {
    	int id = this.level.getID() + 1;
    	final ILevel level;
    	
    	try {
    		level = this.model.getLevelByID(id);
			if (level != null) {
				for (final Entity e : this.level.getEntities()) {
					e.destroy();
				}
				this.initLevel(level);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void initLevel(ILevel level) {
		this.level = level;
		java.lang.System.out.println(this.level.getEntities());
		for (final Entity e : this.level.getEntities()) {
			engine.addEntity(e);
		}
    }
}
