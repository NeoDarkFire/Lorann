package view;

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
    void displayMessage(String message);
    
    public void addSprite(Entity entity);
    public void removeSprite(Entity entity);
    public void displayLevel(ILevel level);
    public boolean isPressed(Action input);
    public boolean isReleased(Action input);
    public Direction getInputDirection();
    public void refresh();
    
}
