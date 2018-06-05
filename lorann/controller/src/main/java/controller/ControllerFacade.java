 package controller;

import java.sql.SQLException;
import java.util.Collection;

import ecs.Engine;
import ecs.Entity;
import model.ILevel;
import model.IModel;
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
    
    /** Flag to load the next level */
    private boolean loadNextlevel;

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
		
		engine._debug = true;

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
//		this.engine.addSystem(new MovementSystem(this));
		this.engine.addSystem(new AnimationSystem(this));
		
		this.engine.addSystem(new GateSystem(this));
		this.engine.addSystem(new ExitSystem(this));
    }

    /**
     * Start.
     * @throws InterruptedException 
     *
     * @throws SQLException
     *             the SQL exception
     */
    @Override
    public void start() {
    	long currentTime;
    	double dt;
    	double fps = 8.0;
    	lastTime = -1;
    	
    	while (true) {
    		currentTime = System.currentTimeMillis();
	    	dt = (currentTime - lastTime)/1000.0;
	    	
	    	if (dt*fps >= 1.0) {
	    		lastTime = currentTime;
	    		this.view.updateInputs();
	    		engine.update(dt);
	    		
	    		if (this.loadNextlevel) {
	    			this.loadNextLevel();
	    			this.loadNextlevel = false;
	    		}
	    		
	    		this.view.refresh();
	    	}
	    	// Sleep a bit, to not over-use the CPU.
	    	try {
	    		Thread.sleep(1);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
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
    	this.loadNextlevel = true;
    }
    
    private void loadNextLevel() {
    	final int id = this.level.getID() + 1;
    	ILevel level;
    	
		level = this.model.getLevelByID(id);
		if (level == null) {
			level = this.model.getLevelByID(1);
		}
		// Copy entities to remove into an array
		Collection<Entity> entities = this.engine.getEntitities();
		Entity[] array = new Entity[entities.size()];
		entities.toArray(array);
		
		for (final Entity e : array) {
			engine.removeEntity(e.getID());
		}
		this.initLevel(level);
    }
    
    public void initLevel(ILevel level) {
    	java.lang.System.out.println("[Controller] Loading level #" + level.getID() + "...");
    	
		this.level = level;
		this.view.setLevel(level);
		// Copy entities to add into an array
		Collection<Entity> entities = this.level.getEntities();
		Entity[] array = new Entity[entities.size()];
		entities.toArray(array);
		
		for (final Entity e : array) {
			engine.addEntity(e);
		}
    }
}
