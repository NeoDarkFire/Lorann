package model.components;

import java.awt.Image;
import java.util.List;

import ecs.Component;

/**
 * <h1>Permits to an Entity to have an animation</h1>
 *
 */
public class AnimationComponent implements Component {
	
	/**
	 * list of all the images
	 */
	public List<Image> images;
	/**
	 * position in the list
	 */
	public int id;
	/**
	 * if the animation runs or stops
	 */
	public boolean play;

}
