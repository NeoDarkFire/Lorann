package view;

import ecs.Entity;
import model.Direction;
import model.ILevel;

/**
 * <h1>The Interface IView.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public interface IView {

    /**
     * Display message.
     *
     * @param message
     *            the message
     */
    void addSprite(Entity entity);
    void removeSprite(Entity entity);
    boolean isPressed(Action input);
    boolean isReleased(Action input);
    Direction getInputDirection();
	void setLevel(ILevel level);
	void refresh();
	void updateInputs();
    
}
