package model.components;

import java.awt.Image;
import java.util.List;

import ecs.Component;

public class AnimationComponent implements Component {
	
	public List<Image> images;
	public int id;
	public boolean play;

}
