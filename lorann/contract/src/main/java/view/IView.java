package view;

import ecs.Entity;
import model.Direction;
import model.ILevel;

/**
 * <h1>The Interface IView.</h1>
 *
 */
public interface IView {
    void addSprite(Entity entity);
    void removeSprite(Entity entity);
    boolean isPressed(Action input);
    boolean isReleased(Action input);
    Direction getInputDirection();
	void setLevel(ILevel level);
	void refresh();
	void updateInputs();
    
}
