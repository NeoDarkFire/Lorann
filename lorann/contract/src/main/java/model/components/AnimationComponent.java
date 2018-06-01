package model.components;

import java.awt.Image;
import java.util.ArrayList;
import ecs.Component;

public class AnimationComponent implements Component {
	
	public ArrayList<Image> images;
	public int id;
	public boolean play;

}
